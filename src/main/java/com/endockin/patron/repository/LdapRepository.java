package com.endockin.patron.repository;

import com.endockin.patron.model.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Hashtable;

import javax.annotation.Resource;
import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;

/**
 * Provides classic LDAP authentication to Endava LDAP. 
 *
 */

@Component
public class LdapRepository {

    private static final String LDAP_SEARCH_BASE = "ldap.search.base";
    private static final String LDAP_URL = "ldap.url";
    private static final String LDAP_CONTEXT_FACTORY = "ldap.context.factory";
    
    private static final Logger LOG = LoggerFactory.getLogger(LdapRepository.class);

    @Resource
    private Environment env;
    
    /**
	 * Build the LDAP environment using a map of properties.
	 * 
	 * @param ldapUsername
	 *            username for connecting to LDAP
	 * @param ldapPassword
	 *            password for connecting to LDAP
	 * @return an instance of {@link javax.naming.ldap.InitialLdapContext}
	 * @throws NamingException
	 *             LDAP specific exception
	 */
	private LdapContext buildLdapContext(String ldapUsername,
			String ldapPassword) throws NamingException {
		final Hashtable<String, String> ldapEnv = new Hashtable<String, String>();
		ldapEnv.put(Context.INITIAL_CONTEXT_FACTORY, env.getRequiredProperty(LDAP_CONTEXT_FACTORY));
		ldapEnv.put(Context.PROVIDER_URL, env.getRequiredProperty(LDAP_URL));
		ldapEnv.put(Context.SECURITY_AUTHENTICATION, "simple");
		ldapEnv.put(Context.SECURITY_PRINCIPAL, ldapUsername + "@" + "endava");
		ldapEnv.put(Context.SECURITY_CREDENTIALS, ldapPassword);

		// the following is helpful in debugging errors
		// env.put("com.sun.jndi.ldap.trace.ber", System.err);
		return new InitialLdapContext(ldapEnv, null);
	}

	/**
	 *
	 * @param username
	 *            Ive logging username
	 * @param password
	 *            Ive logging password
	 * @return an Ive {@link models.User} instance out of the LDAP user details.
	 * @throws NamingException
	 *             LDAP specific exception.
	 */
	public User doAuthentication(final String username, final String password)
			throws NamingException {
		final LdapContext ldapContext = buildLdapContext(username, password);

		final SearchResult srLdapUser = findUserByAccountName(ldapContext,
				env.getRequiredProperty(LDAP_SEARCH_BASE), username);
		if (srLdapUser == null) {
			LOG.info("User doesn't exist in ldap.Maybe user not configured OK?=>"
					+ username);
			return null;
		}

		return getUserAttributes(srLdapUser);
	}

	private User getUserAttributes(SearchResult result) throws NamingException{
	    Attributes attrs = result.getAttributes();
	    User user = new User();
	    user.setEmail((String) attrs.get("mail").get());
	    user.setFirstName((String) attrs.get("givenName").get());
	    user.setLastName((String) attrs.get("sn").get());
	    return user;
	}
	
	/**
	 * Cleanup for final version.
	 * 
	 * @param ctx
	 * @param ldapSearchBase
	 * @param accountName
	 * @return
	 * @throws NamingException
	 */
	private static SearchResult findUserByAccountName(final DirContext ctx,
			final String ldapSearchBase, final String accountName)
			throws NamingException {

		String groupFilter = "(&(objectClass=user)(mailNickname=" + accountName
				+ "))";

		final SearchControls searchControls = new SearchControls();
		searchControls.setSearchScope(SearchControls.SUBTREE_SCOPE);

		final NamingEnumeration<SearchResult> groupResults = ctx.search(
				ldapSearchBase, groupFilter, searchControls);

		SearchResult searchResult = null;
		if (groupResults.hasMoreElements()) {
			searchResult = groupResults.nextElement();

			// make sure there is not another item available, there should be
			// only 1 match
			if (groupResults.hasMoreElements()) {
				LOG.warn("Matched multiple users for the accountName: "
						+ accountName);
				return null;
			}
		}

		return searchResult;
	}
}

