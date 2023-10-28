package edu.sandbox.springweb.https.server.controller;

import edu.sandbox.springweb.https.server.dto.ServerResource;
import edu.sandbox.springweb.https.server.services.ServerResourceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ServerResourceController {

    private final ServerResourceService serverResourceService;

    @GetMapping("/api/server-resource")
    public ServerResource getServerResource() {
        return serverResourceService.getServerResource();
    }
}