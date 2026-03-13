package space.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import space.domain.requests.AddSatelliteRequest;
import space.domain.requests.MissionRequest;
import space.domain.requests.MissionTargetType;
import space.domain.satellites.CommunicationSatelliteParam;
import space.domain.satellites.ImagingSatelliteParam;
import space.domain.satellites.Satellite;
import space.domain.satellites.SatelliteConstellation;
import space.domain.satellites.SatelliteParam;
import space.repository.ConstellationRepository;

@SpringBootTest
@DisplayName("Интеграционные тесты для SpaceOperationCenterService")
class SpaceOperationCenterServiceTest {
    
    @Autowired
    private SpaceOperationCenterService spaceOperationCenterService;

    @Autowired
    private ConstellationRepository constellationRepository;

    @Test
    @DisplayName("Добавление спутников в группировку через фасад")
    void testAddSatellite() {
        String constellationName = "Constellation-1";
        String comSatName = "ComSat-1";
        String imgSatName = "ImgSat-1";

        SatelliteParam comParam = new CommunicationSatelliteParam(comSatName, 0.9, 500);
        SatelliteParam imgParam = new ImagingSatelliteParam(imgSatName, 0.8, 2.5);
        AddSatelliteRequest request = new AddSatelliteRequest(
                                    constellationName,
                                    List.of(comParam, imgParam)
        );

        spaceOperationCenterService.addSatellite(request);

        SatelliteConstellation constellation = constellationRepository.getConstellation(constellationName);
        assertNotNull(constellation);
        assertEquals(2, constellation.getSatellites().size());

        List<String> satelliteNames = constellation.getSatellites().stream()
                                    .map(Satellite::getName)
                                    .toList();
        assertTrue(satelliteNames.contains(comSatName));
        assertTrue(satelliteNames.contains(imgSatName));
    }

    void testExecuteConstellationMission() {
        String constellationName = "MissionConstellation";
        String satName = "MissionSat";

        SatelliteParam comParam = new CommunicationSatelliteParam(satName, 0.9, 500);
        AddSatelliteRequest addRequest = new AddSatelliteRequest(
            constellationName,
            List.of(comParam)
        );
        spaceOperationCenterService.addSatellite(addRequest);

        MissionRequest missionRequest = new MissionRequest(
                                        MissionTargetType.FULL_CONSTELLATION,
                                        constellationName,
                                        null,
                                        null
        );
        spaceOperationCenterService.executeMission(missionRequest);

        SatelliteConstellation constellation = constellationRepository.getConstellation(constellationName);
        Satellite satellite = constellation.getSatellites().get(0);
        assertTrue(satellite.getState().isActive());
    }
}
