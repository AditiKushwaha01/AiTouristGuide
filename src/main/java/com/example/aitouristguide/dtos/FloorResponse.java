package com.example.aitouristguide.dtos;

import lombok.Data;

@Data
public class FloorResponse {
    private Long id;
    private String name;
    private int floorNumber;
    private Long buildingId; // Only include ID, not entire object
    private String mapData;
}
