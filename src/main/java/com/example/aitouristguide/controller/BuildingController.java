package com.example.aitouristguide.controller;

import com.example.aitouristguide.model.Building;
import com.example.aitouristguide.service.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RestMapping("/api/buildings")
public class BuildingController {

    @Autowired
    private BuildingService buildingService;

    //create a building for specific admin
    @PostMapping("admin/{adminId}")
    public ResponseEntity<Building> createBuilding (
            @PathVariable Long adminId,
            @RequestBody Building building) {
        Building createdBuilding = buildingService.createBuilding(adminId, building);
        return ResponseEntity.ok(createdBuilding);
    }

    // Get all buildings for an admin
    @GetMapping("/admin/{adminId}")
    public ResponseEntity<List<Building>> getBuildingsByAdmin(@PathVariable Long adminId) {
        List<Building> buildings = buildingService.getBuildingsByAdmin(adminId);
        return ResponseEntity.ok(buildings);
    }

    // Get building by ID
    @GetMapping("/{id}")
    public ResponseEntity<Building> getBuildingById(@PathVariable Long id) {
        Building building = buildingService.getBuildingById(id);
        return ResponseEntity.ok(building);
    }

    // Update building
    @PutMapping("/{id}")
    public ResponseEntity<Building> updateBuilding(
            @PathVariable Long id,
            @RequestBody Building buildingDetails) {
        Building updatedBuilding = buildingService.updateBuilding(id, buildingDetails);
        return ResponseEntity.ok(updatedBuilding);
    }

    // Delete building
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBuilding(@PathVariable Long id) {
        buildingService.deleteBuilding(id);
        return ResponseEntity.noContent().build();
    }

    // Search buildings by name
    @GetMapping("/search")
    public ResponseEntity<List<Building>> searchBuildings(@RequestParam String name) {
        List<Building> buildings = buildingService.searchBuildingByName(name);
        return ResponseEntity.ok(buildings);
    }
}
