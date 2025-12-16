package com.example.aitouristguide.service;

import com.example.aitouristguide.model.Admin;
import com.example.aitouristguide.model.Building;
import com.example.aitouristguide.repository.AdminRepository;
import com.example.aitouristguide.repository.BuildingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.lang.*;

@Service
public class BuildingService {

    @Autowired
    private BuildingRepository buildingRepository;

    @Autowired
    private AdminRepository adminRepository;

    //create a new building
    public Building createBuilding(Long adminId, Building building) {
        Admin admin = adminRepository.findById(adminId)
                .orElseThrow(() -> new RuntimeException("Admin not found"));

        // Check if building name already exists for this admin
        if (buildingRepository.existsByNameAndAdminId(building.getName(), adminId)) {
            throw new RuntimeException("Building with name '" + building.getName() + "' already exists for this admin");
        }

        building.setAdmin(admin);
        return buildingRepository.save(building);
    }

    //Get all buildings for an admin
    public List<Building> getBuildingsByAdmin (Long adminId) {
        return buildingRepository.findByAdminId(adminId);
    }

    //get building by building id
    public Building getBuildingById(Long id) {
        return buildingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Building not found"));
    }

    public Building updateBuilding(Long id, Building buildingDetails) {
        Building building = getBuildingById(id);

        if(buildingDetails.getName() != null &&
                !buildingDetails.getName().equals(building.getName())) {
            building.setName(buildingDetails.getName());
        }
        if(buildingDetails.getAddress() != null &&
                !buildingDetails.getAddress().equals(building.getAddress())) {
            building.setAddress(buildingDetails.getAddress());
        }

        return buildingRepository.save(building);
    }

    //delete building
    public void deleteBuilding(Long id) {
        Building building = getBuildingById(id);
        buildingRepository.delete(building);
    }

    //search buildings by name
    public List<Building> searchBuildingByName(String name) {
        return buildingRepository.findByNameContainingIgnoreCase(name);
    }
}
