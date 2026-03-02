package space.services;

import space.domain.Satellite;
import space.domain.SatelliteParam;

public interface SatelliteService {
    Satellite createSatellite(SatelliteParam param);
}
