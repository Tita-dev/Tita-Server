package com.example.jwt.repository;

import com.example.jwt.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);  //추후 Optional을 사용하여 리팩토링진행

    User findByName(String name);

    User findByEmail(String email);

    boolean existsByUsername(String username);

    boolean existsByName(String name);

}
