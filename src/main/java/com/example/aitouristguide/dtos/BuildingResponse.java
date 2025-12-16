package com.example.aitouristguide.dtos;

import lombok.Data;
import java.util.List;

@Data
public class BuildingResponse {
    private Long id;
    private String name;
    private String address;
    private List<FloorResponse> floors;
    private AdminResponse admin;
}
