package edu.sandbox.springweb.https.server.services.impl;

import edu.sandbox.springweb.https.server.dto.ServerResource;
import edu.sandbox.springweb.https.server.services.ServerResourceService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class InMemoryServerResourceServiceImpl implements ServerResourceService {

    @Override
    public ServerResource getServerResource() {
        return new ServerResource(UUID.randomUUID());
    }
}