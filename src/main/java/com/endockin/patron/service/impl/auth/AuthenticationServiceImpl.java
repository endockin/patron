package com.endockin.patron.service.impl.auth;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;

import com.endockin.patron.model.Authentication;
import com.endockin.patron.model.User;
import com.endockin.patron.repository.AuthenticationRepository;
import com.endockin.patron.repository.UserRepository;
import com.endockin.patron.service.auth.AuthenticationService;
import com.endockin.patron.service.auth.AuthenticationServiceException;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
  private static final Logger LOG = org.slf4j.LoggerFactory.getLogger(AuthenticationService.class);

  private static final String DIGEST_ALG = "SHA-256";
  private static final String AUTHENTICATION_SECRET_PHRASE = "myC00lSecret";
  private static final String FIELD_DELIMITER = "\n";
  private static final int SESSION_VALIDITY_IN_MINUTES = 60;

  @Autowired
  private UserRepository userRepo;

  @Autowired
  private AuthenticationRepository authRepo;

  @Override
  public Authentication authenticate(User user) throws AuthenticationServiceException {
    if (!userExists(user)) {
      throw new AuthenticationServiceException("Invalid user.");
    }

    Authentication authentication = new Authentication();
    authentication.setGeneratedAt(DateTime.now().toDate());
    authentication.setValidUntil(DateTime.now().plusMinutes(SESSION_VALIDITY_IN_MINUTES).toDate());
    authentication.setUserEmail(user.getEmail());
    authentication.setSalt(UUID.randomUUID().toString());
    authentication.setKey(this.generateApiKey(authentication));

    authRepo.save(authentication);

    return authentication;
  }

  private boolean userExists(User user) throws AuthenticationServiceException {
    return userRepo.findByEmailAndPassword(user.getEmail(), user.getPassword()) != null;
  }

  private String generateApiKey(Authentication a) {
    String stringToEncrypt = a.getSalt() + FIELD_DELIMITER + a.getUserEmail() + FIELD_DELIMITER
        + a.getGeneratedAt() + FIELD_DELIMITER + a.getValidUntil();

    try {
      MessageDigest digest = MessageDigest.getInstance(DIGEST_ALG);
      digest.update(AUTHENTICATION_SECRET_PHRASE.getBytes());
      digest.update(stringToEncrypt.getBytes());

      return Base64Utils.encodeToString(digest.digest());
    } catch (NoSuchAlgorithmException ex) {
      LOG.error("Encoding problem.", ex);
    }

    return null;
  }

  @Override
  public void deauthenticate(String key) throws AuthenticationServiceException {
    Authentication auth = this.authRepo.findByKey(key);
    if (auth == null) {
      throw new AuthenticationServiceException("API key not in session so can not deauthenticate.");
    } else {
      this.authRepo.deleteByKey(key);
    }
  }

  @Override
  public Authentication isAuthenticated(String key) throws AuthenticationServiceException {
    Authentication auth = this.authRepo.findByKey(key);
    if (auth == null) {
      throw new AuthenticationServiceException("API key not in session.");
    }
    
    if (new DateTime(auth.getValidUntil()).isBeforeNow()) {
      throw new AuthenticationServiceException("API key expired! Please re-authenticate");
    }

    return auth;
  }

}
