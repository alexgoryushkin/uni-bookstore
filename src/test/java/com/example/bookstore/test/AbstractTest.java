package com.example.bookstore.test;

import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.bookstore.Bookstore;
import com.example.bookstore.test.config.TestConfig;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { TestConfig.class, Bookstore.class} )
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@TestPropertySource("classpath:test.datasource.properties")
public abstract class AbstractTest {

}
