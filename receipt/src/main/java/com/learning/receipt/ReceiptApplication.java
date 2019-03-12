package com.learning.receipt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class ReceiptApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(ReceiptApplication.class, args);
    }

}
