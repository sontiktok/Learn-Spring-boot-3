package com.tsdev.identityservice.repository;
import com.tsdev.identityservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
    //Jpa se tu tao ra cau query
    boolean existsByUsername(String username);
}
