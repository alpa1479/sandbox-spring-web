package edu.sandbox.springweb.https.client.services.impl;

import edu.sandbox.springweb.https.client.dto.ServerResource;
import edu.sandbox.springweb.https.client.services.ServerResourceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class ServerResourceServiceImpl implements ServerResourceService {

    private final RestTemplate template;

    @Override
    public ServerResource getServerResource() {
        return template.getForObject("https://localhost:8443/api/server-resource", ServerResource.class);
    }
}