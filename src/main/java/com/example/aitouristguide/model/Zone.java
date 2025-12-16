package com.example.aitouristguide.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "zones")
@Data
public class Zone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String zoneId; // Unique identifier within map

    @Column
    private String description;

    @Column(name = "x_position")
    private Integer x;

    @Column(name = "y_position")
    private Integer y;

    @Column
    private Integer width;

    @Column
    private Integer height;

    @Column
    private String color;

    @ManyToOne
    @JoinColumn(name = "floor_id", nullable = false)
    private Floor floor;

    @Column(name = "created_at")
    private java.time.LocalDateTime createdAt;
}
