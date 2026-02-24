package space.factory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import space.constants.CommunicationSatelliteConstants;
import space.constants.ImagingSatelliteConstants;
import space.domain.CommunicationSatellite;
import space.domain.ImagingSatellite;
import space.domain.Satellite;

@DisplayName("Тест для SatelliteFactory")
class SatelliteFactoryTest {
    private static SatelliteFactory comFactory;
    private static SatelliteFactory imgFactory;

    @BeforeAll
    static void setup() {
        comFactory = new CommunicationSatelliteFactory();
        imgFactory = new ImagingSatelliteFactory();
    }

    @Test
    @DisplayName("Фабрика спутников связи создает спутник с дефолтными параметрами")
    void communicationFactoryCreatesSatelliteWithDefaultParameters() {
        String name = "Связь";
        double batteryLevel = 0.8;

        Satellite satellite = comFactory.createSatellite(name, batteryLevel);

        assertNotNull(satellite);
        assertInstanceOf(CommunicationSatellite.class, satellite);
        assertEquals(name, satellite.getName());
        assertEquals(batteryLevel, satellite.getEnergy().getBatteryLevel(), 0.001);

        CommunicationSatellite comSatellite = (CommunicationSatellite) satellite;
        assertEquals(CommunicationSatelliteConstants.DEFAULT_BANDWIDTH, comSatellite.getBandwidth(), 0.001);
    }

    @Test
    @DisplayName("Фабрика спутников связи создает спутник с пользовательскими параметрами")
    void communicationFactoryCreatesSatelliteWithUserParameters() {
        String name = "Связь";
        double batteryLevel = 0.8;
        double bandwidth = 500.0;

        Satellite satellite = comFactory.createSatelliteWithParameter(name, batteryLevel, bandwidth);

        assertNotNull(satellite);
        assertInstanceOf(CommunicationSatellite.class, satellite);
        assertEquals(name, satellite.getName());
        assertEquals(batteryLevel, satellite.getEnergy().getBatteryLevel(), 0.001);

        CommunicationSatellite comSatellite = (CommunicationSatellite) satellite;
        assertEquals(bandwidth, comSatellite.getBandwidth(), 0.001);
    }

    @Test
    @DisplayName("Фабрика спутников зондирования создает спутник с дефолтными параметрами")
    void imagingFactoryCreatesSatelliteWithDefaultParameters() {
        String name = "ДЗЗ";
        double batteryLevel = 0.8;

        Satellite satellite = imgFactory.createSatellite(name, batteryLevel);

        assertNotNull(satellite);
        assertInstanceOf(ImagingSatellite.class, satellite);
        assertEquals(name, satellite.getName());
        assertEquals(batteryLevel, satellite.getEnergy().getBatteryLevel(), 0.001);

        ImagingSatellite imgSatellite = (ImagingSatellite) satellite;
        assertEquals(ImagingSatelliteConstants.DEFAULT_RESOLUTION, imgSatellite.getResolution(), 0.001);
    }

    @Test
    @DisplayName("Фабрика спутников зондирования создает спутник с пользовательскими параметрами")
    void imagingFactoryCreatesSatelliteWithUserParameters() {
        String name = "ДЗЗ";
        double batteryLevel = 0.8;
        double resolution = 1.0;

        Satellite satellite = imgFactory.createSatelliteWithParameter(name, batteryLevel, resolution);

        assertNotNull(satellite);
        assertInstanceOf(ImagingSatellite.class, satellite);
        assertEquals(name, satellite.getName());
        assertEquals(batteryLevel, satellite.getEnergy().getBatteryLevel(), 0.001);

        ImagingSatellite imgSatellite = (ImagingSatellite) satellite;
        assertEquals(resolution, imgSatellite.getResolution(), 0.001);
    }
}
