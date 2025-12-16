package com.example.aitouristguide.repository;

import com.example.aitouristguide.model.Path;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PathRepository extends JpaRepository<Path, Long> {
    List<Path> findByFloorId(Long floorId);
    void deleteByFloorId(Long floorId);
}
