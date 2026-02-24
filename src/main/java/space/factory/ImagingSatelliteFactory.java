package space.factory;

import org.springframework.stereotype.Component;

import space.constants.ImagingSatelliteConstants;
import space.domain.ImagingSatellite;
import space.domain.Satellite;

@Component
public class ImagingSatelliteFactory implements SatelliteFactory {
    @Override
    public Satellite createSatellite(String name, double batteryLevel) {
        return new ImagingSatellite(name, batteryLevel, ImagingSatelliteConstants.DEFAULT_RESOLUTION);
    }

    @Override
    public Satellite createSatelliteWithParameter(String name, double batteryLevel, double parameter) {
        return new ImagingSatellite(name, batteryLevel, parameter);
    }
}
