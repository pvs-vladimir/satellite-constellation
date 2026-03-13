package space.services;

import space.domain.satellites.Satellite;
import space.domain.satellites.SatelliteParam;

public interface SatelliteService {
    Satellite createSatellite(SatelliteParam param);
}
