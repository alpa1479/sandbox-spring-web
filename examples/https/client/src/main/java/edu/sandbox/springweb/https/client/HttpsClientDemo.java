package edu.sandbox.springweb.https.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class HttpsClientDemo {

    public static void main(String[] args) {
        SpringApplication.run(HttpsClientDemo.class, args);
    }
}
