package com.example.Entity;

//Resource.java
import jakarta.persistence.*;

@Entity
@Table(name = "resources")
public class Resource {
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;

 private String name;
 private String type; // room, vehicle, equipment
 private String description;

 // Getters and Setters
 public Long getId() { return id; }
 public void setId(Long id) { this.id = id; }

 public String getName() { return name; }
 public void setName(String name) { this.name = name; }

 public String getType() { return type; }
 public void setType(String type) { this.type = type; }

 public String getDescription() { return description; }
 public void setDescription(String description) { this.description = description; }
}

