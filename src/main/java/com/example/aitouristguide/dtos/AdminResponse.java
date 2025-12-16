package com.example.aitouristguide.dtos;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.*;

public class AdminResponse {
    private Long id;
    private String username;
    private String email;
    private List<BuildingResponse> buildings;

    @Data
    public static class BuildingResponse {
        private Long id;
        private String name;
        private String address;
    }
}
