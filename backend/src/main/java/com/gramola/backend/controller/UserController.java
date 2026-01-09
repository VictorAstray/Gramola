package com.gramola.backend.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin; // Importamos el servicio nuevo
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.gramola.backend.model.User;
import com.gramola.backend.service.SpotifyService;
import com.gramola.backend.service.UserService;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    @Autowired
    private UserService userService;
    
    @Autowired
    private SpotifyService spotifyService; // Inyectamos Spotify

    @PostMapping("/login")
    public User login(@RequestBody Map<String, String> body) {
        User user = this.userService.login(body.get("email"), body.get("pwd"));
        if (user == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Credenciales incorrectas");
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

        if (!pwd1.equals(pwd2)) throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Las contraseñas no coinciden");
        
        try {
            this.userService.register(bar, email, pwd1, clientId, clientSecret);
        } catch (Exception e) {
             throw new ResponseStatusException(HttpStatus.CONFLICT, "Error al registrar");
        }
    }

    // --- NUEVO ENDPOINT DE BÚSQUEDA ---
    @PostMapping("/search")
    public List<Map<String, Object>> search(@RequestBody Map<String, String> body) {
        String email = body.get("email");
        String query = body.get("query");
        
        // 1. Recuperamos al usuario para tener sus claves de Spotify
        // (Usamos un truco: el login busca por mail y pass, aquí asumimos que ya está logueado y usamos el repo directamente o el login si no queremos tocar el servicio)
        // Para hacerlo RÁPIDO sin modificar UserService, necesitamos un método findByEmail.
        // Pero como "UserService.login" pide password, vamos a ser prácticos:
        // En un caso real, Angular mandaría el ID o un token.
        // Aquí, Angular mandará el objeto User completo que guardó en el login.
        
        String clientId = body.get("clientId");
        String clientSecret = body.get("clientSecret");

        if (clientId == null || clientSecret == null) {
             throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Faltan credenciales de Spotify");
        }

        // 2. Conseguimos el token
        String token = spotifyService.getAccessToken(clientId, clientSecret);
        if (token == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "No se pudo conectar con Spotify. Revisa Client ID/Secret");
        }

        // 3. Buscamos y devolvemos
        return spotifyService.searchTracks(query, token);
    }
}