package com.example.aitouristguide.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "connectors")
@Data
public class Connector {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String connectorId;

    @Column(nullable = false)
    private String type; // "STAIRS", "ELEVATOR", "ESCALATOR"

    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "from_floor_id", nullable = false)
    private Floor fromFloor;

    @ManyToOne
    @JoinColumn(name = "to_floor_id", nullable = false)
    private Floor toFloor;

    @Column(name = "from_x")
    private Integer fromX;

    @Column(name = "from_y")
    private Integer fromY;

    @Column(name = "to_x")
    private Integer toX;

    @Column(name = "to_y")
    private Integer toY;

    @Column(name = "created_at")
    private java.time.LocalDateTime createdAt;
}