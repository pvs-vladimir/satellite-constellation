package space.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class SatelliteParam {
    private SatelliteType type;
    private String name;
    private double batteryLevel;
}
