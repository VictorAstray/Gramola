package com.gramola.backend.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gramola.backend.model.User;
import com.gramola.backend.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User login(String email, String pwd) {
        return this.userRepository.findByEmailAndPwd(email, pwd);
    }

    public void register(String bar, String email, String pwd1, String clientId, String clientSecret) {
        User user = new User(email, bar, pwd1, clientId, clientSecret);
        String token = UUID.randomUUID().toString();
        user.setCreationTokenId(token);
        this.userRepository.save(user);

        System.out.println("---------------------------------------------------------");
        System.out.println("SIMULACIÓN DE EMAIL:");
        System.out.println("Hola " + bar + ", confirma tu cuenta aquí:");
        System.out.println("http://localhost:8080/users/confirmToken/" + email + "?token=" + token);
        System.out.println("---------------------------------------------------------");
    }
}