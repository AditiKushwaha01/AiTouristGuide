package com.example.aitouristguide.service;

import com.example.aitouristguide.model.*;
import com.example.aitouristguide.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class MapDataService {

    @Autowired
    private MapDataRepository mapDataRepository;

    @Autowired
    private ZoneRepository zoneRepository;

    @Autowired
    private PathRepository pathRepository;

    @Autowired
    private ConnectorRepository connectorRepository;

    @Autowired
    private FloorRepository floorRepository;

    // Save complete map data
    @Transactional
    public MapData saveMapData(Long floorId, String mapJson) {
        Floor floor = floorRepository.findById(floorId)
                .orElseThrow(() -> new RuntimeException("Floor not found"));

        // Check if map data already exists
        MapData existingMapData = mapDataRepository.findByFloorId(floorId).orElse(null);

        if (existingMapData != null) {
            existingMapData.setMapJson(mapJson);
            return mapDataRepository.save(existingMapData);
        } else {
            MapData mapData = new MapData();
            mapData.setFloor(floor);
            mapData.setMapJson(mapJson);
            return mapDataRepository.save(mapData);
        }
    }

    // Get map data for a floor
    public MapData getMapDataByFloorId(Long floorId) {
        return mapDataRepository.findByFloorId(floorId)
                .orElseThrow(() -> new RuntimeException("Map data not found for floor: " + floorId));
    }

    // Save individual zone
    @Transactional
    public Zone saveZone(Long floorId, Zone zone) {
        Floor floor = floorRepository.findById(floorId)
                .orElseThrow(() -> new RuntimeException("Floor not found"));

        zone.setFloor(floor);
        zone.setCreatedAt(java.time.LocalDateTime.now());

        return zoneRepository.save(zone);
    }

    // Get all zones for a floor
    public List<Zone> getZonesByFloorId(Long floorId) {
        return zoneRepository.findByFloorId(floorId);
    }

    // Save connector between floors
    @Transactional
    public Connector saveConnector(Connector connector) {
        connector.setCreatedAt(java.time.LocalDateTime.now());
        return connectorRepository.save(connector);
    }

    // Get connectors for a floor
    public List<Connector> getConnectorsByFloorId(Long floorId) {
        return connectorRepository.findByFromFloorIdOrToFloorId(floorId, floorId);
    }

    // Clear all map data for a floor
    @Transactional
    public void clearMapData(Long floorId) {
        zoneRepository.deleteByFloorId(floorId);
        pathRepository.deleteByFloorId(floorId);

        MapData mapData = mapDataRepository.findByFloorId(floorId).orElse(null);
        if (mapData != null) {
            mapDataRepository.delete(mapData);
        }
    }
}
