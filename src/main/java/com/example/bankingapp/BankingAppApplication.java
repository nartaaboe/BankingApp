package com.example.bankingapp;

import com.example.bankingapp.config.RsaKeyConfigProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties(RsaKeyConfigProperties.class)
@SpringBootApplication
public class BankingAppApplication {
    public static void main(String[] args) {
        SpringApplication.run(BankingAppApplication.class, args);
    }
}
