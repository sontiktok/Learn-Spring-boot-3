package com.tsdev.identityservice.repository;
import com.tsdev.identityservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    //Jpa se tu tao ra cau query
    boolean existsByUsername(String username);
    Optional<User> findByUsername(String username);
}
