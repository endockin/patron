package com.endockin.patron.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

@PropertySource(value = { "classpath:application.properties" }, ignoreResourceNotFound = true)
@Import({ JpaConfiguration.class })
@ComponentScan(basePackages = { "com.endockin" }, useDefaultFilters = true)
public class TestConfiguration {

}
