package com.example.Entity;

//Reservation.java
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "reservations")
public class Reservation {
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;

 @ManyToOne
 private User user;

 @ManyToOne
 private Resource resource;

 @Enumerated(EnumType.STRING)
 private Status status;

 private BigDecimal price;
 private LocalDateTime startTime;
 private LocalDateTime endTime;

 // Getters and Setters
 public Long getId() { return id; }
 public void setId(Long id) { this.id = id; }

 public User getUser() { return user; }
 public void setUser(User user) { this.user = user; }

 public Resource getResource() { return resource; }
 public void setResource(Resource resource) { this.resource = resource; }

 public Status getStatus() { return status; }
 public void setStatus(Status status) { this.status = status; }

 public BigDecimal getPrice() { return price; }
 public void setPrice(BigDecimal price) { this.price = price; }

 public LocalDateTime getStartTime() { return startTime; }
 public void setStartTime(LocalDateTime startTime) { this.startTime = startTime; }

 public LocalDateTime getEndTime() { return endTime; }
 public void setEndTime(LocalDateTime endTime) { this.endTime = endTime; }
}

