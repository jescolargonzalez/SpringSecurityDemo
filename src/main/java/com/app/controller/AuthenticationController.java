package com.app.controller;

import com.app.controller.dto.AuthCreateUserRequest;
import com.app.controller.dto.AuthLoginRequest;
import com.app.controller.dto.AuthResponse;
import com.app.service.UserDetailServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@PreAuthorize("permitAll()")
@RequestMapping("/auth")
@Tag(name = "Authentication", description = "Controller for Authentication")
public class AuthenticationController {

    @Autowired
    private UserDetailServiceImpl userDetailService;

  @Operation(
        summary = "Login User",
        description = "Authenticate a user and return the authentication token along with user details.",
        tags = {"Authentication"},
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                description = "Authentication request with username and password",
                required = true,
                content = @Content(
                        mediaType = "application/json",
                        schema = @Schema(implementation = AuthLoginRequest.class)
                )
        ),
        responses = {
                @ApiResponse(
                        responseCode = "200",
                        description = "Successful authentication",
                        content = @Content(
                                mediaType = "application/json",
                                schema = @Schema(implementation = AuthResponse.class)
                        )
                )
        }
    )
    @PostMapping("/sign-up")
    public ResponseEntity<AuthResponse> register(@RequestBody @Valid AuthCreateUserRequest authCreateUserRequest) throws IllegalAccessException {
        return new ResponseEntity<>(this.userDetailService.createUser(authCreateUserRequest), HttpStatus.CREATED);
    }
    @PostMapping("/log-in")
    public ResponseEntity<AuthResponse> login(@RequestBody @Valid AuthLoginRequest userRequest) {
        return new ResponseEntity<>(this.userDetailService.loginUser(userRequest), HttpStatus.OK);
    }

}
