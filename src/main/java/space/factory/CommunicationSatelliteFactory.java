package space.factory;

import org.springframework.stereotype.Component;

import space.domain.CommunicationSatellite;
import space.domain.CommunicationSatelliteParam;
import space.domain.Satellite;
import space.domain.SatelliteParam;
import space.domain.SatelliteType;

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
