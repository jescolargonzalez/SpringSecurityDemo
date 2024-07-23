package com.app.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/method")
@PreAuthorize("denyAll()")
public class TestAuthController {
    @GetMapping("/pruebas")
    @PreAuthorize("hasAuthority('READ')")
    public String helloGet() {
        return "Hello World (READ)";
    }
    @PostMapping("/pruebas")
    @PreAuthorize("hasAuthority('CREATE')")
    public String helloPost() {
        return "Hello World (CREATE)";
    }
    @PreAuthorize("hasAuthority('UPDATE')")
    @PutMapping("/pruebas")
    public String helloPut() {
        return "Hello World (UPDATE)";
    }
    @PreAuthorize("hasAuthority('DELETE')")
    @DeleteMapping("/pruebas")
    public String helloDelete() {
        return "Hello World (DELETE)";
    }
    @PreAuthorize("hasAuthority('REFACTOR')")
    @PatchMapping("/pruebas")
    public String helloPatch() {
        return "Hello World (reFACTOR)";
    }


}
