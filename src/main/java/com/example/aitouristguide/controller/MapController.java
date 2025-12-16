package com.example.aitouristguide.controller;

import com.example.aitouristguide.model.*;
import com.example.aitouristguide.service.MapDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/maps")
public class MapController {

    @Autowired
    private MapDataService mapService;

    // Save complete map JSON
    @PostMapping("/floor/{floorId}/save")
    public ResponseEntity<MapData> saveMap(
            @PathVariable Long floorId,
            @RequestBody Map<String, Object> mapData) {

        // Convert map to JSON string
        String mapJson = convertMapToJson(mapData);

        MapData savedMap = mapService.saveMapData(floorId, mapJson);
        return ResponseEntity.ok(savedMap);
    }

    // Get map data for floor
    @GetMapping("/floor/{floorId}")
    public ResponseEntity<MapData> getMapByFloorId(@PathVariable Long floorId) {
        try {
            MapData mapData = mapService.getMapDataByFloorId(floorId);
            return ResponseEntity.ok(mapData);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Save individual zone
    @PostMapping("/floor/{floorId}/zones")
    public ResponseEntity<Zone> saveZone(
            @PathVariable Long floorId,
            @RequestBody Zone zone) {

        Zone savedZone = mapService.saveZone(floorId, zone);
        return ResponseEntity.ok(savedZone);
    }

    // Get all zones for floor
    @GetMapping("/floor/{floorId}/zones")
    public ResponseEntity<List<Zone>> getZonesByFloorId(@PathVariable Long floorId) {
        List<Zone> zones = mapService.getZonesByFloorId(floorId);
        return ResponseEntity.ok(zones);
    }

    // Save connector
    @PostMapping("/connectors")
    public ResponseEntity<Connector> saveConnector(@RequestBody Connector connector) {
        Connector savedConnector = mapService.saveConnector(connector);
        return ResponseEntity.ok(savedConnector);
    }

    // Get connectors for floor
    @GetMapping("/floor/{floorId}/connectors")
    public ResponseEntity<List<Connector>> getConnectorsByFloorId(@PathVariable Long floorId) {
        List<Connector> connectors = mapService.getConnectorsByFloorId(floorId);
        return ResponseEntity.ok(connectors);
    }

    // Clear map data
    @DeleteMapping("/floor/{floorId}/clear")
    public ResponseEntity<Void> clearMapData(@PathVariable Long floorId) {
        mapService.clearMapData(floorId);
        return ResponseEntity.noContent().build();
    }

    // Helper method to convert map to JSON
    private String convertMapToJson(Map<String, Object> mapData) {
        // Simple conversion (in production, use Jackson ObjectMapper)
        StringBuilder json = new StringBuilder("{");
        for (Map.Entry<String, Object> entry : mapData.entrySet()) {
            json.append("\"").append(entry.getKey()).append("\":");
            if (entry.getValue() instanceof String) {
                json.append("\"").append(entry.getValue()).append("\"");
            } else {
                json.append(entry.getValue());
            }
            json.append(",");
        }
        if (json.charAt(json.length()-1) == ',') {
            json.deleteCharAt(json.length()-1);
        }
        json.append("}");
        return json.toString();
    }
}
