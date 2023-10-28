package edu.sandbox.springweb.https.client.controller;

import edu.sandbox.springweb.https.client.dto.ServerResource;
import edu.sandbox.springweb.https.client.services.ServerResourceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ServerResourceController {

    private final ServerResourceService serverResourceService;

    @GetMapping("/api/proxy/server-resource")
    public ServerResource getServerResource() {
        return serverResourceService.getServerResource();
    }
}