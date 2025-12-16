package com.example.aitouristguide.model;

import jakarta.persistence.*;
import lombok.Data;

    @Entity
    @Table(name = "map_data")
    @Data
    public class MapData {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @OneToOne
        @JoinColumn(name = "floor_id", nullable = false, unique = true)
        private Floor floor;

        @Column(columnDefinition = "TEXT", nullable = false)
        private String mapJson;

        @Column(name = "created_at")
        private java.time.LocalDateTime createdAt;

        @Column(name = "updated_at")
        private java.time.LocalDateTime updatedAt;

        @PrePersist
        protected void onCreate() {
            createdAt = java.time.LocalDateTime.now();
            updatedAt = java.time.LocalDateTime.now();
        }

        @PreUpdate
        protected void onUpdate() {
            updatedAt = java.time.LocalDateTime.now();
        }
}
