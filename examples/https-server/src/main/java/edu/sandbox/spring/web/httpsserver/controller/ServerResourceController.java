package edu.sandbox.spring.web.httpsserver.controller;

import edu.sandbox.spring.web.httpsserver.dto.ServerResource;
import edu.sandbox.spring.web.httpsserver.services.ServerResourceService;
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