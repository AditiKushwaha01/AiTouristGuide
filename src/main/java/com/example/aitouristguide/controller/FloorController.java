package com.example.aitouristguide.controller;

import com.example.aitouristguide.model.Floor;
import com.example.aitouristguide.service.FloorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/floors")
public class FloorController {


    @Autowired
    private FloorService floorService;

    //create a floors for a building
    @PostMapping("/building/{buildingId}")
    public ResponseEntity<Floor> createFloor(
            @PathVariable Long buildingId,
            @RequestBody Floor floor) {
        try {
            Floor createdFloor = floorService.createFloor(buildingId, floor);
            return ResponseEntity.ok(createdFloor);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    // get all floors for a building
    @GetMapping("/building/{buildingId}")
    public ResponseEntity<List<Floor>> getFloorsByBuilding(@PathVariable Long buildingId) {
        List<Floor> floors = floorService.getFloorsByBuilding(buildingId);
        return ResponseEntity.ok(floors);
    }

    //get floor by id
    @GetMapping("/{id}")
    public ResponseEntity<Floor> getFloorById(@PathVariable Long id) {
        try {
            Floor floor = floorService.getFloorById(id);
            return ResponseEntity.ok(floor);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // get floor by id
    @GetMapping("/building/{buildingId}/number/{floorNumber}")
    public ResponseEntity<Floor> getFloorByBuildingAndNumber(@PathVariable Long buildingId,
                                                             @PathVariable int floorNumber) {
        Floor floor = floorService.getFloorByBuildingAndNumber(buildingId, floorNumber);
        if (floor == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(floor);
    }

    //Update floor
    @PutMapping("/{id}")
    public ResponseEntity<Floor> updateFloor (@PathVariable Long id,
                                              @RequestBody Floor floorDetails) {
        try {
            Floor updatedFloor = floorService.updateFloor(id, floorDetails);
            return ResponseEntity.ok(updatedFloor);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    //Delete floor
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFloor(@PathVariable Long id) {
        try {
            floorService.deleteFloor(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
