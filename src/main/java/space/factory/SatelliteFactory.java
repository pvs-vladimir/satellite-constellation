package space.factory;

import space.domain.satellites.Satellite;
import space.domain.satellites.SatelliteParam;
import space.domain.satellites.SatelliteType;

public interface SatelliteFactory {
    Satellite createSatelliteWithParameter(SatelliteParam param);
    boolean isSatelliteTypeSupported(SatelliteType type);
}
