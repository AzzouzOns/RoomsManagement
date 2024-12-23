package com.monsite.reservation.roomreservation.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Type cannot be null")
    @Size(min = 1, max = 50, message = "Type must be between 1 and 50 characters")
    private String type;

    @NotNull(message = "Name cannot be null")
    @Size(min = 1, max = 100, message = "Name must be between 1 and 100 characters")
    private String name;

    private int capacity;
    private boolean available;

    @Column(length = 500)
    private String description;

    @Column(length = 255)
    private String imageUrl;


    public Room(String type, String name, int capacity, boolean available, String description, String imageUrl) {
        this.type = type;
        this.name = name;
        this.capacity = capacity;
        this.available = available;
        this.description = description;
        this.imageUrl = imageUrl;
    }
}
