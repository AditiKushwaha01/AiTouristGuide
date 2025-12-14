package com.example.aitouristguide.service;

import com.example.aitouristguide.model.Building;
import com.example.aitouristguide.model.Floor;
import com.example.aitouristguide.repository.BuildingRepository;
import com.example.aitouristguide.repository.FloorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FloorService {

    @Autowired
    private FloorRepository floorRepository;

    @Autowired
    private BuildingRepository buildingRepository;

    //create a new floor for a building
    public Floor createFloor(Long buildingId, Floor floor) {
        Building building = buildingRepository.findById(buildingId)
                .orElseThrow(() -> new RuntimeException("Building not found"));

        //check if floor number already exists in this building
        Floor existingFloor = floorRepository.findByBuildingIdAndFloorNumber(buildingId, floor.getFloorNumber());
        if(existingFloor != null) {
            throw new RuntimeException("Floor number " + floor.getFloorNumber() + " already exists in this building");
        }

        floor.setBuilding(building);
        return floorRepository.save(floor);
    }

    //get floor by id
    public Floor getFloorById(Long id) {
        return floorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Floor not found"));
    }

    //Update floor
    public Floor updateFloor(Long id, Floor floorDetails) {
        Floor floor = getFloorById(id);

        if(floorDetails.getName() != null) {
            floor.setName(floorDetails.getName());
        }
        if(floorDetails.getFloorNumber() != 0 && floorDetails.getFloorNumber() != floor.getFloorNumber()) {
            Long buildingId = floor.getBuilding().getId();
            Floor existingFloor = floorRepository.findByBuildingIdAndFloorNumber(buildingId, floorDetails.getFloorNumber());
            if(existingFloor != null && !existingFloor.getId().equals(id)) {
                throw new RuntimeException("Floor number " + floorDetails.getFloorNumber() + " already exists in this building");
            }
            floor.setFloorNumber(floorDetails.getFloorNumber());
        }
        if(floorDetails.getMapData() != null) {
            floor.setMapData(floorDetails.getMapData());
        }
        return floorRepository.save(floor);
    }

    //delete floor
    public void deleteFloor(Long id) {
        Floor floor = getFloorById(id);
        floorRepository.delete(floor);
    }

    //get specific floor by building and floor number
    public Floor getFloorByBuildingAndNumber(Long buildingId, int floorNumber) {
        Floor floor = floorRepository.findByBuildingIdAndFloorNumber(buildingId, floorNumber);
        if(floor == null) {
            throw new RuntimeException("Floor not found");
        }
        return floor;
    }
}
