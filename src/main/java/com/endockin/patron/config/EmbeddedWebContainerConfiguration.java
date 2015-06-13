package com.endockin.patron.config;

import com.endockin.patron.filter.SecurityFilter;
import javax.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmbeddedWebContainerConfiguration implements EmbeddedServletContainerCustomizer {

    private static final int PORT = 8080;

    @Override
    public void customize(ConfigurableEmbeddedServletContainer container) {
        container.setPort(PORT);
    }

}
