package space.factory;

import org.springframework.stereotype.Component;

import space.domain.ImagingSatellite;
import space.domain.ImagingSatelliteParam;
import space.domain.Satellite;
import space.domain.SatelliteParam;
import space.domain.SatelliteType;

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
