package com.example.aitouristguide.controller;

import com.example.aitouristguide.dtos.BuildingResponse;
import com.example.aitouristguide.model.Building;
import com.example.aitouristguide.service.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/buildings")
public class BuildingController {

    @Autowired
    private BuildingService buildingService;

    //create a building for specific admin
    @PostMapping("admin/{adminId}")
    public ResponseEntity<Building> createBuilding (
            @PathVariable Long adminId,
            @RequestBody Building building) {
        try {
            Building createdBuilding = buildingService.createBuilding(adminId, building);
            return ResponseEntity.ok(createdBuilding);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
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
        try {
            Building building = buildingService.getBuildingById(id);
            return ResponseEntity.ok(building);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Update building
    @PutMapping("/{id}")
    public ResponseEntity<Building> updateBuilding(
            @PathVariable Long id,
            @RequestBody Building buildingDetails) {
        try {
            Building updatedBuilding = buildingService.updateBuilding(id, buildingDetails);
            return ResponseEntity.ok(updatedBuilding);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete building
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBuilding(@PathVariable Long id) {
        try {
            buildingService.deleteBuilding(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Search buildings by name
    @GetMapping("/search")
    public ResponseEntity<List<Building>> searchBuildings(@RequestParam String name) {
        List<Building> buildings = buildingService.searchBuildingByName(name);
        return ResponseEntity.ok(buildings);
    }
}
