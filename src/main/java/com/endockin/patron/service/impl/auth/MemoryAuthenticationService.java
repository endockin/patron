package com.endockin.patron.service.impl.auth;

import com.endockin.patron.model.User;
import com.endockin.patron.resource.auth.Authentication;
import com.endockin.patron.service.auth.AuthenticationService;
import com.endockin.patron.service.auth.AuthenticationServiceException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;

@Service
public class MemoryAuthenticationService implements AuthenticationService {

    private static final String ENCRYPTION_ALG = "AES";
    private static final String AUTHENTICATION_SECRET_PHRASE = "d3440b69126d9c186fddc713b18b0002";
    private static final SecretKeySpec SECRET_KEY = new SecretKeySpec(AUTHENTICATION_SECRET_PHRASE.getBytes(), ENCRYPTION_ALG);
    private static final String FIELD_DELIMITER = "\n";

    private static final Logger LOG = LoggerFactory.getLogger(MemoryAuthenticationService.class);

    private static final String VALID_USER = "user";
    private static final String VALID_PASSWORD = "12dea96fec20593566ab75692c9949596833adc9";
    private static final int SESSION_VALIDITY_IN_MINUTES = 60;

    private final Map<String, Authentication> keyMap = new ConcurrentHashMap<>();

    @Override
    public Authentication authenticate(User u) throws AuthenticationServiceException {
        if (!isValidUser(u)) {
            throw new AuthenticationServiceException("Invalid user.");
        }

        Authentication a = new Authentication();
        a.setGeneratedAt(new Date());
        a.setValidUntil(new DateTime().plusMinutes(SESSION_VALIDITY_IN_MINUTES).toDate());
        a.setUserId(u.getId());
        a.setSalt(UUID.randomUUID().toString());
        a.setKey(generateApiKey(a));

        keyMap.put(a.getKey(), a);

        return a;
    }

    @Override
    public void deauthenticate(String key) throws AuthenticationServiceException {
        if (!keyMap.containsKey(key)) {
            throw new AuthenticationServiceException("API key not in session so can not deauthenticate.");
        }

        LOG.debug("Deauthenticated user []", getAuthenticationFromKey(key));
        keyMap.remove(key);
    }

    @Override
    public Authentication isAuthenticated(String key) throws AuthenticationServiceException {
        if (!keyMap.containsKey(key)) {
            throw new AuthenticationServiceException("API key not in session.");
        }

        return keyMap.get(key);
    }

    private static boolean isValidUser(User u) {
        return VALID_USER.equals(u.getId()) && VALID_PASSWORD.equals(u.getPassword());
    }

    private String generateApiKey(Authentication a) {
        String key = null;
        String stringToEncrypt = a.getSalt() + FIELD_DELIMITER + a.getUserId() + FIELD_DELIMITER
                + a.getGeneratedAt() + FIELD_DELIMITER + a.getValidUntil();
        try {

            Cipher cipher = Cipher.getInstance(ENCRYPTION_ALG);
            cipher.init(Cipher.ENCRYPT_MODE, SECRET_KEY);

            key = Base64Utils.encodeToString(cipher.doFinal(stringToEncrypt.getBytes()));
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException |
                IllegalBlockSizeException | BadPaddingException ex) {
            LOG.error("Encoding problem.", ex);
        }

        return key;
    }

    private Authentication getAuthenticationFromKey(String key) {
        Authentication a = null;

        try {
            Cipher cipher = Cipher.getInstance(ENCRYPTION_ALG);
            cipher.init(Cipher.DECRYPT_MODE, SECRET_KEY);

            String keyPlain = new String(cipher.doFinal(Base64Utils.decodeFromString(key)));

            LOG.debug("In Plain [{}]", keyPlain);
            StringTokenizer tokenizer = new StringTokenizer(keyPlain, FIELD_DELIMITER);

            a = new Authentication();
            a.setSalt(tokenizer.nextToken());
            a.setUserId(tokenizer.nextToken());
            //TODO deserialize dates too
            // a.setGeneratedAt(DateTime.parse(tokenizer.nextToken()).toDate());
            // a.setValidUntil(DateTime.parse(tokenizer.nextToken()).toDate());
            a.setKey(key);

        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException |
                IllegalBlockSizeException | BadPaddingException ex) {
            LOG.error("Decryption problem.", ex);
        }

        return a;
    }

}
