package com.example.aitouristguide.repository;

import com.example.aitouristguide.model.Floor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FloorRepository extends JpaRepository<Floor, Long> {
    List <Floor> findByBuildingId (Long BuildingId);
    Floor findByBuildingIdAndFloorNumber(Long buildingId, int floorNumber);
    List<Floor> findByBuildingIdOrderByFloorNumberAsc(Long buildingId);
    boolean existsByBuildingIdAndFloorNumber(Long buildingId, int floorNumber);
}
