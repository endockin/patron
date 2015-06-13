package com.endockin.patron.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@WebFilter(urlPatterns = "/api/*", filterName = "corsFilter")
public class CrossOriginRequestServletFilter implements Filter {

    private static final Logger LOG = LoggerFactory.getLogger(CrossOriginRequestServletFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        LOG.debug("Initialized filter [{}]", this.getClass().getSimpleName());
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.setHeader("Access-Control-Allow-Origin", "*");
        httpResponse.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        httpResponse.setHeader("Access-Control-Allow-Headers", "Origin, Content-Type, Accepts");
        httpResponse.setHeader("Access-Control-Max-Age", "3600");
        chain.doFilter(request, httpResponse);
    }

    @Override
    public void destroy() {
        LOG.debug("Destroyed filter [{}]", this.getClass().getSimpleName());
    }

}
