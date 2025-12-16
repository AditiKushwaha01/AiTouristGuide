package com.example.aitouristguide.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "building_id", nullable = false)
    @JsonBackReference("building-floors")
    private Building building;

    // In your existing Floor.java, add:
    @OneToOne(mappedBy = "floor", cascade = CascadeType.ALL)
    private MapData mapData;

    @OneToMany(mappedBy = "floor", cascade = CascadeType.ALL)
    private List<Zone> zones = new ArrayList<>();

    @OneToMany(mappedBy = "floor", cascade = CascadeType.ALL)
    private List<Path> paths = new ArrayList<>();
}
