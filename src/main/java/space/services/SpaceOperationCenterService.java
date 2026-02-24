package space.services;

import org.springframework.stereotype.Service;

import space.domain.Satellite;
import space.domain.SatelliteConstellation;
import space.repository.ConstellationRepository;

@Service
public class SpaceOperationCenterService {
    private final ConstellationRepository repository;

    public SpaceOperationCenterService(ConstellationRepository repository) {
        this.repository = repository;
    }

    public void createAndSaveConstellation(String constellationName) {
        if (!repository.hasConstellation(constellationName)) {
            repository.addConstellation(new SatelliteConstellation(constellationName));
        }
    }

    public void addSatelliteToConstellation(String constellationName, Satellite satellite) {
        if (repository.hasConstellation(constellationName)) {
            repository.getConstellation(constellationName).addSatellite(satellite);
        }
    }

    public void activateAllSatellites(String constellationName) {
        if (repository.hasConstellation(constellationName)) {
            SatelliteConstellation constellation = repository.getConstellation(constellationName);
            System.out.println(String.format("===== АКТИВАЦИЯ СПУТНИКОВ В ГРУППИРОВКЕ: %s =====",
                                             constellation.getConstellationName()));
            for (Satellite satellite : constellation.getSatellites()) {
                satellite.activate();
            }
        }
    }

    public void deactivateAllSatellites(String constellationName) {
        if (repository.hasConstellation(constellationName)) {
            SatelliteConstellation constellation = repository.getConstellation(constellationName);
            System.out.println(String.format("===== ДЕАКТИВАЦИЯ СПУТНИКОВ В ГРУППИРОВКЕ: %s =====",
                                             constellation.getConstellationName()));
            for (Satellite satellite : constellation.getSatellites()) {
                satellite.deactivate();
            }
        }
    }

    public void executeConstellationMission(String constellationName) {
        if (repository.hasConstellation(constellationName)) {
            SatelliteConstellation constellation = repository.getConstellation(constellationName);
            System.out.println(String.format("===== ВЫПОЛНЕНИЕ МИССИЙ ДЛЯ ГРУППИРОВКИ: %s =====",
                                             constellation.getConstellationName()));
            constellation.executeAllMissions();
        }
    }

    public void showConstellationStatus(String constellationName) {
        if (repository.hasConstellation(constellationName)) {
            SatelliteConstellation constellation = repository.getConstellation(constellationName);
            System.out.println(String.format("===== СТАТУС ГРУППИРОВКИ: %s =====",
                                             constellation.getConstellationName()));
            System.out.println("Количество спутников: " + constellation.getSatellites().size());
            for (Satellite satellite : constellation.getSatellites()) {
                System.out.println(satellite);
            }
        }
    }
}