package com.example.aitouristguide.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "paths")
@Data
public class Path {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String pathId;

    @ManyToOne
    @JoinColumn(name = "start_zone_id")
    private Zone startZone;

    @ManyToOne
    @JoinColumn(name = "end_zone_id")
    private Zone endZone;

    @Column(columnDefinition = "TEXT")
    private String points; // JSON array of {x, y} points

    @Column
    private String color;

    @Column
    private Integer width;

    @ManyToOne
    @JoinColumn(name = "floor_id", nullable = false)
    private Floor floor;

    @Column(name = "created_at")
    private java.time.LocalDateTime createdAt;
}
