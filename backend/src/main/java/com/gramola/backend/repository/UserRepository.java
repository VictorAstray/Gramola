package com.gramola.backend.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gramola.backend.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, String> {
    // Spring Data crea esta consulta automáticamente por el nombre del método
    User findByEmailAndPwd(String email, String pwd);
}