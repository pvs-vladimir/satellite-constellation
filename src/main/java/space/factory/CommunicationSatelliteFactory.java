package space.factory;

import org.springframework.stereotype.Component;

import space.constants.CommunicationSatelliteConstants;
import space.domain.CommunicationSatellite;
import space.domain.Satellite;

@Component
public class CommunicationSatelliteFactory implements SatelliteFactory {
    @Override
    public Satellite createSatellite(String name, double batteryLevel) {
        return new CommunicationSatellite(name, batteryLevel, CommunicationSatelliteConstants.DEFAULT_BANDWIDTH);
    }

    @Override
    public Satellite createSatelliteWithParameter(String name, double batteryLevel, double parameter) {
        return new CommunicationSatellite(name, batteryLevel, parameter);
    }
}
