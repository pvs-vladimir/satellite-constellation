package space.factory;

import org.springframework.stereotype.Component;

import space.domain.satellites.ImagingSatellite;
import space.domain.satellites.ImagingSatelliteParam;
import space.domain.satellites.Satellite;
import space.domain.satellites.SatelliteParam;
import space.domain.satellites.SatelliteType;

@Component
public class ImagingSatelliteFactory implements SatelliteFactory {
    @Override
    public Satellite createSatelliteWithParameter(SatelliteParam param) {
        if (SatelliteType.IMAGE.equals(param.getType())) {
            ImagingSatelliteParam imagingParam = (ImagingSatelliteParam) param;
            return new ImagingSatellite(
                imagingParam.getName(),
                imagingParam.getBatteryLevel(),
                imagingParam.getResolution()
            );
        }
        throw new RuntimeException("Данный тип параметров не поддерживается!");
    }

    @Override
    public boolean isSatelliteTypeSupported(SatelliteType type) {
        return SatelliteType.IMAGE.equals(type);
    }
}
