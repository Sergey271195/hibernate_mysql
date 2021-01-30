package com.illuminator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringEntrypoint {

    private static final Logger log =
            LoggerFactory.getLogger(SpringEntrypoint.class);

    public static void main(String[] args) {
        SpringApplication.run(SpringEntrypoint.class, args);
    }

}
