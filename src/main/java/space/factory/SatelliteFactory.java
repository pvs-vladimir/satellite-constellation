package space.factory;

import space.domain.Satellite;
import space.domain.SatelliteParam;
import space.domain.SatelliteType;

public interface SatelliteFactory {
    Satellite createSatelliteWithParameter(SatelliteParam param);
    boolean isSatelliteTypeSupported(SatelliteType type);
}
