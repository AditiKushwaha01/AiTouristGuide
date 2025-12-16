package com.example.aitouristguide.repository;

import com.example.aitouristguide.model.Connector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConnectorRepository extends JpaRepository<Connector, Long> {
    List<Connector> findByFromFloorIdOrToFloorId(Long floorId, Long floorId1);

    Connector save(Connector connector);
}
