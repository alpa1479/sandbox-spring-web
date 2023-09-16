package edu.sandbox.spring.web.https.server.services.impl;

import edu.sandbox.spring.web.https.server.dto.ServerResource;
import edu.sandbox.spring.web.https.server.services.ServerResourceService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class InMemoryServerResourceServiceImpl implements ServerResourceService {

    @Override
    public ServerResource getServerResource() {
        return new ServerResource(UUID.randomUUID());
    }
}