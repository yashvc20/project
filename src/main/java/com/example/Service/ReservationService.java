package com.example.Service;

import org.springframework.stereotype.Service;

import com.example.Entity.Reservation;
import com.example.Entity.Resource;
import com.example.Entity.Status;
import com.example.Entity.User;
import com.example.Repository.ReservationRepository;
import com.example.Repository.ResourceRepository;
import com.example.Repository.UserRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.math.BigDecimal;
import java.util.Optional;

@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;
    private final ResourceRepository resourceRepository;

    public ReservationService(
        ReservationRepository reservationRepository,
        UserRepository userRepository,
        ResourceRepository resourceRepository
    ) {
        this.reservationRepository = reservationRepository;
        this.userRepository = userRepository;
        this.resourceRepository = resourceRepository;
    }

    public Reservation createReservation(Long userId, Long resourceId, Reservation reservation) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Resource resource = resourceRepository.findById(resourceId)
                .orElseThrow(() -> new RuntimeException("Resource not found"));

        reservation.setUser(user);
        reservation.setResource(resource);
        reservation.setStatus(Status.PENDING);

        return reservationRepository.save(reservation);
    }

    public Page<Reservation> getReservationsForUser(User user, Pageable pageable) {
        return reservationRepository.findByUser(user, pageable);
    }

    public Page<Reservation> getAllReservations(Pageable pageable) {
        return reservationRepository.findAll(pageable);
    }

    public Page<Reservation> filterReservations(Status status, BigDecimal min, BigDecimal max, Pageable pageable) {
        return reservationRepository.findByStatusAndPriceBetween(status, min, max, pageable);
    }

    public Optional<Reservation> getReservationById(Long id) {
        return reservationRepository.findById(id);
    }

    public Reservation updateReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    public void deleteReservation(Long id) {
        reservationRepository.deleteById(id);
    }
}

