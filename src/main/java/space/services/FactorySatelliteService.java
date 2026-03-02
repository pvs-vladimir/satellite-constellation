package space.services;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import space.domain.Satellite;
import space.domain.SatelliteParam;
import space.factory.SatelliteFactory;

@RequiredArgsConstructor
@Service
public class FactorySatelliteService implements SatelliteService {
    private final List<SatelliteFactory> factories;

    @Override
    public Satellite createSatellite(SatelliteParam param) {
        SatelliteFactory factory = factories.stream()
            .filter(satelliteFactory -> satelliteFactory.isSatelliteTypeSupported(param.getType()))
            .findFirst()
            .orElseThrow(() -> new RuntimeException("Данный тип параметров не поддерживается!"));
        return factory.createSatelliteWithParameter(param);
    }
}
