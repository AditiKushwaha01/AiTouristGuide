package com.example.aitouristguide.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "floors")
@Data
public class Floor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int floorNumber;

    @ManyToOne
    @JoinColumn(name = "building_id")
    private Building building;

    @Column(columnDefinition = "TEXT")
    private String mapData; //json map data
}
