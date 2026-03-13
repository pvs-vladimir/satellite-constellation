package space.domain.requests;

import java.util.Set;

import space.domain.satellites.SatelliteType;

public record MissionRequest (MissionTargetType targetType,
                              String constellationName,
                              String satelliteName,
                              Set<SatelliteType> targetTypes) {
}
