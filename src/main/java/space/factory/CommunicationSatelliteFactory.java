package space.factory;

import org.springframework.stereotype.Component;

import space.domain.satellites.CommunicationSatellite;
import space.domain.satellites.CommunicationSatelliteParam;
import space.domain.satellites.Satellite;
import space.domain.satellites.SatelliteParam;
import space.domain.satellites.SatelliteType;

@Component
public class CommunicationSatelliteFactory implements SatelliteFactory {
    @Override
    public Satellite createSatelliteWithParameter(SatelliteParam param) {
        if (SatelliteType.COMMUNICATION.equals(param.getType())) {
            CommunicationSatelliteParam communicationParam = (CommunicationSatelliteParam) param;
            return new CommunicationSatellite(
                communicationParam.getName(),
                communicationParam.getBatteryLevel(),
                communicationParam.getBandwidth()
            );
        }
        throw new RuntimeException("Данный тип параметров не поддерживается!");
    }

    @Override
    public boolean isSatelliteTypeSupported(SatelliteType type) {
        return SatelliteType.COMMUNICATION.equals(type);
    }
}
