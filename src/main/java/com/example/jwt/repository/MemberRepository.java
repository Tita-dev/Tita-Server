package com.example.jwt.repository;

import com.example.jwt.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface MemberRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);

    boolean existsByUsername(String username);

    boolean existsByName(String name);

}
