package com.example.aitouristguide.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "buildings")
@Data
public class Building {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(nullable = false)
        private String name;

        @Column(nullable = false)
        private String address;

        @OneToMany(mappedBy = "building", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
        @JsonIgnore  //helps avoid infinite loop
        private List<Floor> floors = new ArrayList<>();

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "admin_id", nullable = false)
        private Admin admin;

        //Helper method to add floor
        public void addFloor(Floor floor) {
                floors.add(floor);
                floor.setBuilding(this);
        }
}
