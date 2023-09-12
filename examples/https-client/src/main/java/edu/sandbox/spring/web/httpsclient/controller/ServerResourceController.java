package edu.sandbox.spring.web.httpsclient.controller;

import edu.sandbox.spring.web.httpsclient.dto.ServerResource;
import edu.sandbox.spring.web.httpsclient.services.ServerResourceService;
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