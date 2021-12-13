package com.example.jwt.repository;

import com.example.jwt.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    User findByName(String name);

    User findByEmail(String email);

    boolean existsByUsername(String username);

    boolean existsByName(String name);

}
