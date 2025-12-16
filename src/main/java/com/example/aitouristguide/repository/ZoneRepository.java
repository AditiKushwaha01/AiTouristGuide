package com.example.aitouristguide.repository;

import com.example.aitouristguide.model.Zone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public interface ZoneRepository extends JpaRepository<Zone, Long> {
    List<Zone> findByFloorId(Long floorId);
    Optional<Zone> findByFloorIdAndZoneId(Long floorId, String zoneId);
    List<Zone> deleteByFloorId(Long floorId);
}
