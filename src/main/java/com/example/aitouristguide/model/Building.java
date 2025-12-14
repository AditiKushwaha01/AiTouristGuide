package com.example.aitouristguide.model;

import jakarta.persistence.*;
import lombok.Data;
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

        @OneToMany(mappedBy = "building", cascade = CascadeType.ALL)
        private List<Floor> floors;

        @ManyToOne
        @JoinColumn(name = "admin_id")
        private Admin admin;
}
