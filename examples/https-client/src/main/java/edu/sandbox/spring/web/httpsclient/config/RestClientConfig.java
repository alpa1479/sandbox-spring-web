package edu.sandbox.spring.web.httpsclient.config;

import org.apache.hc.client5.http.config.ConnectionConfig;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManagerBuilder;
import org.apache.hc.client5.http.ssl.SSLConnectionSocketFactoryBuilder;
import org.apache.hc.core5.pool.PoolConcurrencyPolicy;
import org.apache.hc.core5.pool.PoolReusePolicy;
import org.apache.hc.core5.ssl.SSLContextBuilder;
import org.apache.hc.core5.util.TimeValue;
import org.apache.hc.core5.util.Timeout;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.security.GeneralSecurityException;

@Configuration
public class RestClientConfig {

    @Bean
    public RestTemplate restTemplate(RestClientProperties properties, RestTemplateBuilder builder)
            throws GeneralSecurityException, IOException {
        var keystorePassword = properties.keystorePassword().toCharArray();

        var sslContext = SSLContextBuilder.create()
                .loadTrustMaterial(properties.truststore().getURL(), properties.truststorePassword().toCharArray())
                .loadKeyMaterial(properties.keystore().getURL(), keystorePassword, keystorePassword)
                .build();

        var connectionManager = PoolingHttpClientConnectionManagerBuilder.create()
                .setSSLSocketFactory(SSLConnectionSocketFactoryBuilder.create()
                        .setSslContext(sslContext)
                        .build())
                .setPoolConcurrencyPolicy(PoolConcurrencyPolicy.STRICT)
                .setConnPoolPolicy(PoolReusePolicy.LIFO)
                .setDefaultConnectionConfig(ConnectionConfig.custom()
                        .setSocketTimeout(Timeout.ofMinutes(1))
                        .setConnectTimeout(Timeout.ofMinutes(1))
                        .setTimeToLive(TimeValue.ofMinutes(10))
                        .build())
                .build();

        var client = HttpClients.custom()
                .setConnectionManager(connectionManager)
                .build();

        return builder.requestFactory(() -> new HttpComponentsClientHttpRequestFactory(client)).build();
    }
}
