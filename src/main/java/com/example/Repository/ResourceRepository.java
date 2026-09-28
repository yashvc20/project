package com.example.Repository;

//ResourceRepository.java
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Entity.Resource;

public interface ResourceRepository extends JpaRepository<Resource, Long> {
 // You can add custom queries later if needed
}

