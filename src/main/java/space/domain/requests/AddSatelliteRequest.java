package space.domain.requests;

import java.util.List;

import space.domain.satellites.SatelliteParam;

public record AddSatelliteRequest(String constellationName,
                                  List<SatelliteParam> satelliteParams) {
}
