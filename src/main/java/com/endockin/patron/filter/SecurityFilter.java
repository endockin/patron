package com.endockin.patron.filter;

import com.endockin.patron.service.auth.AuthenticationService;
import com.endockin.patron.service.auth.AuthenticationServiceException;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
@WebFilter(urlPatterns = "/api/*", filterName = "securityFilter")
public class SecurityFilter implements Filter {
    
    private static final String API_KEY_HEADER = "X-Patron-Api-Key";
    private static final Logger LOG = LoggerFactory.getLogger(SecurityFilter.class);
    
    @Autowired
    private AuthenticationService authenticationService;
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        LOG.debug("Initialized filter [{}]", this.getClass().getSimpleName());
    }
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            HttpServletRequest httpRequest = (HttpServletRequest) request;
            if (!httpRequest.getRequestURI().startsWith("/api") || httpRequest.getRequestURI().equals("/api/auth/login")) {
                chain.doFilter(request, response);
                return;
            }
            
            String apiKey = httpRequest.getHeader(API_KEY_HEADER);
            if (StringUtils.isEmpty(apiKey)) {
                throw new AuthenticationServiceException("Header must be present.");
            }
            
            LOG.trace("Filter processing [{}]", httpRequest.getRequestURI());
            authenticationService.isAuthenticated(apiKey);
            chain.doFilter(request, response);
        } catch (AuthenticationServiceException ex) {
            LOG.info("Trying to access protected resource without being authenticated. Cause: {}", ex.getMessage());
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            httpResponse.sendError(HttpStatus.UNAUTHORIZED.value());
        }
    }
    
    @Override
    public void destroy() {
        LOG.debug("Destroyed filter [{}]", this.getClass().getSimpleName());
    }
    
}