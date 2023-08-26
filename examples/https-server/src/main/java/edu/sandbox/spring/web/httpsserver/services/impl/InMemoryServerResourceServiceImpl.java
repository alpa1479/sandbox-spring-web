package edu.sandbox.spring.web.httpsserver.services.impl;

import edu.sandbox.spring.web.httpsserver.dto.ServerResource;
import edu.sandbox.spring.web.httpsserver.services.ServerResourceService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class InMemoryServerResourceServiceImpl implements ServerResourceService {

    @Override
    public ServerResource getServerResource() {
        return new ServerResource(UUID.randomUUID());
    }
}