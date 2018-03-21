package com.example.bookstore.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@EntityScan(basePackages = {"com.example.bookstore.entities", "org.springframework.data.jpa.convert.threeten"})
@PropertySource("classpath:datasource.properties")
public class PersistenceConfig {

}
