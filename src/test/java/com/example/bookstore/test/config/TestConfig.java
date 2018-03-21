package com.example.bookstore.test.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.TestConfiguration;

@TestConfiguration
@EntityScan(basePackages = {"com.example.bookstore.entities", "org.springframework.data.jpa.convert.threeten"})
public class TestConfig {

}
