package com.gramola.backend.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.gramola.backend.service.UserService;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:4200") // Permite que Angular se conecte
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping("/register")
    public void register(@RequestBody Map<String, String> body) {
        String bar = body.get("bar");
        String email = body.get("email");
        String pwd1 = body.get("pwd1");
        String pwd2 = body.get("pwd2");
        String clientId = body.get("clientId");
        String clientSecret = body.get("clientSecret");

        // Comprobación básica de contraseñas
        if (!pwd1.equals(pwd2)) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Las contraseñas no coinciden");
        }

        this.service.register(bar, email, pwd1, clientId, clientSecret);
    }
}