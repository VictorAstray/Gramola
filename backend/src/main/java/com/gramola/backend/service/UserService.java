package com.gramola.backend.service;

import com.gramola.backend.model.User;
import com.gramola.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void register(String bar, String email, String pwd1, String clientId, String clientSecret) {
        // 1. Crear el objeto usuario
        User user = new User(email, bar, pwd1, clientId, clientSecret);
        
        // 2. Generamos un token aleatorio para simular la confirmación
        String token = UUID.randomUUID().toString();
        user.setCreationTokenId(token);

        // 3. Guardamos en Base de Datos
        this.userRepository.save(user);

        // 4. Simulamos el envío de correo (Lo imprimimos por consola para que puedas hacer clic)
        System.out.println("---------------------------------------------------------");
        System.out.println("SIMULACIÓN DE EMAIL:");
        System.out.println("Hola " + bar + ", confirma tu cuenta aquí:");
        System.out.println("http://localhost:8080/users/confirmToken/" + email + "?token=" + token);
        System.out.println("---------------------------------------------------------");
    }
}