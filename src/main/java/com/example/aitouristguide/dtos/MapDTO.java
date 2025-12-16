package com.example.aitouristguide.dtos;

import lombok.Data;
import java.util.List;
import java.util.Map;

@Data
public class MapDTO {
    private Long floorId;
    private String floorName;
    private Map<String, Object> mapData;
    private List<ZoneDTO> zones;
    private List<PathDTO> paths;
    private List<ConnectorDTO> connectors;

    @Data
    public static class ZoneDTO {
        private String id;
        private String name;
        private String type;
        private Integer x;
        private Integer y;
        private Integer width;
        private Integer height;
        private String color;
        private Map<String, Object> properties;
    }

    @Data
    public static class PathDTO {
        private String id;
        private String startZoneId;
        private String endZoneId;
        private List<PointDTO> points;
        private String color;
        private Integer width;
    }

    @Data
    public static class ConnectorDTO {
        private String id;
        private String type;
        private String name;
        private Long fromFloorId;
        private Long toFloorId;
        private PointDTO fromPosition;
        private PointDTO toPosition;
    }

    @Data
    public static class PointDTO {
        private Integer x;
        private Integer y;
    }
}
