package edu.sandbox.springweb.https.client.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.io.Resource;

@ConfigurationProperties(prefix = "application.rest-client.ssl")
public record RestClientProperties(Resource keystore, String keystorePassword,
                                   Resource truststore, String truststorePassword) {
}
