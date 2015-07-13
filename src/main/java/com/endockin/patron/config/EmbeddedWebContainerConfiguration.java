package com.endockin.patron.config;

import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmbeddedWebContainerConfiguration implements EmbeddedServletContainerCustomizer {

  private static final int PORT = 8082;

  @Override
  public void customize(ConfigurableEmbeddedServletContainer container) {
    container.setPort(PORT);
  }

}
