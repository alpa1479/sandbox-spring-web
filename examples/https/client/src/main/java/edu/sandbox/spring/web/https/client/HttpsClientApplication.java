package edu.sandbox.spring.web.https.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class HttpsClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(HttpsClientApplication.class, args);
    }
}
