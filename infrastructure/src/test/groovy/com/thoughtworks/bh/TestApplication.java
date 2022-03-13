package com.thoughtworks.bh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ActiveProfiles;

@SpringBootApplication
@ActiveProfiles("test")
@EnableJpaRepositories
public class TestApplication {

    public static void main(String args[]) {
        SpringApplication.run(TestApplication.class);
    }
}
