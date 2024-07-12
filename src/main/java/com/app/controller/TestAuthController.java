package com.app.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@PreAuthorize("denyAll()")
public class TestAuthController {
//    @GetMapping("/hello")
//    @PreAuthorize("permitAll()")
//    public String hello() {
//        return "Hello World! · [NO SECURED]";
//    }
//
//    @GetMapping("/hello2")
//    @PreAuthorize("hasAuthority('CREATE')")
//    public String hello2() {
//        return "Hello 2 World! · [SECURED]";
//    }
//
//    @GetMapping("/hello-secured")
//    @PreAuthorize("hasAuthority('READ')")
//    public String helloSecured() {
//        return "Accessed secured endpoint";
//    }
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
