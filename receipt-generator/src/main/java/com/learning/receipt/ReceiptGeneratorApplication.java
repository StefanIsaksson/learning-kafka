package com.learning.receipt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class ReceiptGeneratorApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(ReceiptGeneratorApplication.class, args);
    }

}
