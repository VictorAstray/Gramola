package com.gramola.backend.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired; // Importante
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody; // Importa todo para ahorrar líneas
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.gramola.backend.model.User;
import com.gramola.backend.service.UserService;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping("/login")
    public User login(@RequestBody Map<String, String> body) {
        String email = body.get("email");
        String pwd = body.get("pwd");
        
        User user = this.service.login(email, pwd);
        
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Credenciales incorrectas");
        }
        return user;
    }

    @PostMapping("/register")
    public void register(@RequestBody Map<String, String> body) {
        String bar = body.get("bar");
        String email = body.get("email");
        String pwd1 = body.get("pwd1");
        String pwd2 = body.get("pwd2");
        String clientId = body.get("clientId");
        String clientSecret = body.get("clientSecret");

        if (!pwd1.equals(pwd2)) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Las contraseñas no coinciden");
        }
        
        // Pequeña mejora: comprobar si el usuario ya existe antes de registrar
        // (Opcional, pero recomendado para evitar errores SQL feos)
        try {
            this.service.register(bar, email, pwd1, clientId, clientSecret);
        } catch (Exception e) {
             throw new ResponseStatusException(HttpStatus.CONFLICT, "El usuario ya existe o hubo un error");
        }
    }
}