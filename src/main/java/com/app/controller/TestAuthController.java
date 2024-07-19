package com.app.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/method")
@PreAuthorize("denyAll()")
public class TestAuthController {
    @GetMapping("/get")
    @PreAuthorize("hasAuthority('READ')")
    public String helloGet() {
        return "Hello World";
    }
    @PostMapping("/post")
    @PreAuthorize("hasAuthority('CREATE')")
    public String helloPost() {
        return "Hello World";
    }
    @PreAuthorize("hasAuthority('UPDATE')")
    @PutMapping("/put")
    public String helloPut() {
        return "Hello World";
    }
    @PreAuthorize("hasAuthority('DELETE')")
    @DeleteMapping("/delete")
    public String helloDelete() {
        return "Hello World";
    }
    @PreAuthorize("hasAuthority('REFACTOR')")
    @PatchMapping("/patch")
    public String helloPatch() {
        return "Hello World";
    }


}
