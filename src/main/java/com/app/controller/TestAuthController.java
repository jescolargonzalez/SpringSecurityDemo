package com.app.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@PreAuthorize("denyAll()")
public class TestAuthController {

    @GetMapping("/hello")
    @PreAuthorize("permitAll()")
    public String hello() {
        return "Hello World! · [NO SECURED]";
    }

    @GetMapping("/hello2")
    @PreAuthorize("hasAuthority('CREATE')")
    public String hello2() {
        return "Hello 2 World! · [SECURED]";
    }

    @GetMapping("/hello-secured")
    @PreAuthorize("hasAuthority('READ')")
    public String helloSecured() {
        return "Accessed secured endpoint";
    }

}
