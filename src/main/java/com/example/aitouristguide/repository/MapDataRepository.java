package com.example.aitouristguide.repository;

import com.example.aitouristguide.model.MapData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface MapDataRepository extends JpaRepository<MapData, Long> {
    Optional<MapData> findByFloorId(Long floorId);
    boolean existsByFloorId(Long floorId);
}
