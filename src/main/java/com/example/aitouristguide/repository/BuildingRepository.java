package com.example.aitouristguide.repository;


import com.example.aitouristguide.model.Building;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BuildingRepository extends JpaRepository<Building, Long> {
    List<Building> findByAdminId(Long adminId);
    List<Building> findByNameContainingIgnoreCase(String name);
    boolean existsByNameAndAdminId(String name, Long adminId);
}
