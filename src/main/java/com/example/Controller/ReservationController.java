package com.example.Controller;


import com.example.Entity.Reservation;
import com.example.Entity.Status;
import com.example.Entity.User;
import com.example.Service.ReservationService;
import com.example.Service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.security.Principal;

@RestController
@RequestMapping("/reservations")
public class ReservationController {
    private final ReservationService reservationService;
    private final UserService userService;

    public ReservationController(ReservationService reservationService, UserService userService) {
        this.reservationService = reservationService;
        this.userService = userService;
    }

    @PostMapping("/{resourceId}")
    public Reservation createReservation(@PathVariable Long resourceId,
                                         @RequestBody Reservation reservation,
                                         Principal principal) {
        User user = userService.findByEmail(principal.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));
        return reservationService.createReservation(user.getId(), resourceId, reservation);
    }

    @GetMapping
    public Page<Reservation> getReservations(@RequestParam(defaultValue = "0") int page,
                                             @RequestParam(defaultValue = "10") int size,
                                             @RequestParam(required = false) Status status,
                                             @RequestParam(required = false) BigDecimal minPrice,
                                             @RequestParam(required = false) BigDecimal maxPrice,
                                             Principal principal) {
        Pageable pageable = PageRequest.of(page, size);
        User user = userService.findByEmail(principal.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (status != null && minPrice != null && maxPrice != null) {
            return reservationService.filterReservations(status, minPrice, maxPrice, pageable);
        }

        if (user.getRole().name().equals("ADMIN")) {
            return reservationService.getAllReservations(pageable);
        } else {
            return reservationService.getReservationsForUser(user, pageable);
        }
    }
}

