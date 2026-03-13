package space.services;

import org.springframework.stereotype.Service;

import java.util.List;

import lombok.RequiredArgsConstructor;
import space.domain.requests.AddSatelliteRequest;
import space.domain.requests.MissionRequest;
import space.domain.satellites.Satellite;
import space.domain.satellites.SatelliteConstellation;
import space.domain.satellites.SatelliteParam;

@RequiredArgsConstructor
@Service
public class SpaceOperationCenterService {
    private final ConstellationService constellationService;
    private final SatelliteService satelliteService;
    
    public void addSatellite(AddSatelliteRequest request) {
        if (!constellationService.hasConstellation(request.constellationName())) {
            constellationService.createAndSaveConstellation(request.constellationName());
        }

        for (SatelliteParam param : request.satelliteParams()) {
            Satellite satellite = satelliteService.createSatellite(param);
            constellationService.addSatelliteToConstellation(request.constellationName(), satellite);
        }
    }

    public void executeMission(MissionRequest request) {
        switch (request.targetType()) {
            case FULL_CONSTELLATION -> {
                constellationService.activateAllSatellites(request.constellationName());
                constellationService.executeConstellationMission(request.constellationName());
            }
            case CONSTELLATION_TYPES -> {
                SatelliteConstellation constellation = constellationService.getConstellation(request.constellationName());
                List<Satellite> satellites = constellation.getSatellites().stream()
                                    .filter(s -> request.targetTypes().contains(s.getType()))
                                    .toList();

                for (Satellite satellite : satellites) {
                    satellite.activate();
                    satellite.performMission();   
                }
            }
            case SINGLE_SATELLITE -> {
                SatelliteConstellation constellation = constellationService.getConstellation(request.constellationName());
                Satellite satellite = constellation.getSatellites().stream()
                                    .filter(s -> s.getName().equals(request.satelliteName()))
                                    .findFirst()
                                    .orElseThrow(() -> new RuntimeException("Спутник не найден: " + request.satelliteName()));
                satellite.activate();
                satellite.performMission();
            }
        }
    }
}
