package space.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import space.domain.satellites.CommunicationSatellite;
import space.domain.satellites.CommunicationSatelliteParam;
import space.domain.satellites.ImagingSatellite;
import space.domain.satellites.ImagingSatelliteParam;
import space.domain.satellites.Satellite;
import space.domain.satellites.SatelliteParam;

@SpringBootTest
@DisplayName("Тесты для SatelliteService")
class SatelliteServiceTest {
    private static final String NAME = "TestSatellite";
    private static final Double BATTERY_LEVEL = 0.6;
    private static final Double PARAM = 0.1;

    @Autowired
    private SatelliteService satelliteService;

    @Test
    @DisplayName("Создание спутника ДЗЗ")
    void createImagingSatellite() {
        SatelliteParam imagingParam = new ImagingSatelliteParam(NAME, BATTERY_LEVEL, PARAM);

        Satellite satellite = satelliteService.createSatellite(imagingParam);

        assertNotNull(satellite);
        assertInstanceOf(ImagingSatellite.class, satellite);
        ImagingSatellite imagingSatellite = (ImagingSatellite) satellite;

        assertEquals(NAME, imagingSatellite.getName());
        assertEquals(BATTERY_LEVEL, imagingSatellite.getEnergy().getBatteryLevel());
        assertEquals(PARAM, imagingSatellite.getResolution());
    }

    @Test
    @DisplayName("Создание спутника связи")
    void createCommunicationSatellite() {
        SatelliteParam communicationParam = new CommunicationSatelliteParam(NAME, BATTERY_LEVEL, PARAM);

        Satellite satellite = satelliteService.createSatellite(communicationParam);

        assertNotNull(satellite);
        assertInstanceOf(CommunicationSatellite.class, satellite);
        CommunicationSatellite communicationSatellite = (CommunicationSatellite) satellite;

        assertEquals(NAME, communicationSatellite.getName());
        assertEquals(BATTERY_LEVEL, communicationSatellite.getEnergy().getBatteryLevel());
        assertEquals(PARAM, communicationSatellite.getBandwidth());
    }
}
