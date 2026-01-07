package com.gramola.backend.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gramola.backend.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, String> {
    // Aquí podremos añadir métodos extra si los necesitamos
}