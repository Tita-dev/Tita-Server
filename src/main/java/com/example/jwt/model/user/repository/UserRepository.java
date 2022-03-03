package com.example.jwt.model.user.repository;

import com.example.jwt.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);  //추후 Optional을 사용하여 리팩토링진행

    User findByName(String name);

    User findByEmail(String email);

    boolean existsByUsername(String username);

    boolean existsByName(String name);

    User findByUserIdx(Long userIdx);
}