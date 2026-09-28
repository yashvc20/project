package com.example.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Entity.Reservation;
import com.example.Entity.Status;
import com.example.Entity.User;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.math.BigDecimal;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    // Custom query method to fetch reservations for a specific user
    Page<Reservation> findByUser(User user, Pageable pageable);

    // Custom query method to filter by status and price range
    Page<Reservation> findByStatusAndPriceBetween(
        Status status,
        BigDecimal min,
        BigDecimal max,
        Pageable pageable
    );
}
