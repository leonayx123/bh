package com.thoughtworks.bh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
@Profile("!test")
public class BlackHorseApplication {
    public static void main(String args[]) {
        SpringApplication.run(BlackHorseApplication.class);
    }
}
