package com.example.Repository;
//UserRepository.java
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
 Optional<User> findByEmail(String email);
}
