package space.factory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import space.domain.satellites.CommunicationSatellite;
import space.domain.satellites.CommunicationSatelliteParam;
import space.domain.satellites.ImagingSatellite;
import space.domain.satellites.ImagingSatelliteParam;
import space.domain.satellites.Satellite;

@DisplayName("Тесты для SatelliteFactory")
class SatelliteFactoryTest {
    private static SatelliteFactory comFactory;
    private static SatelliteFactory imgFactory;

    @BeforeAll
    static void setup() {
        comFactory = new CommunicationSatelliteFactory();
        imgFactory = new ImagingSatelliteFactory();
    }

    @Test
    @DisplayName("Фабрика спутников связи создает спутник с пользовательскими параметрами")
    void communicationFactoryCreatesSatelliteWithUserParameters() {
        String name = "Связь";
        double batteryLevel = 0.8;
        double bandwidth = 500.0;

        Satellite satellite = comFactory.createSatelliteWithParameter(
            new CommunicationSatelliteParam(name, batteryLevel, bandwidth)
        );

        assertNotNull(satellite);
        assertInstanceOf(CommunicationSatellite.class, satellite);
        assertEquals(name, satellite.getName());
        assertEquals(batteryLevel, satellite.getEnergy().getBatteryLevel(), 0.001);

        CommunicationSatellite comSatellite = (CommunicationSatellite) satellite;
        assertEquals(bandwidth, comSatellite.getBandwidth(), 0.001);
    }

    @Test
    @DisplayName("Фабрика спутников зондирования создает спутник с пользовательскими параметрами")
    void imagingFactoryCreatesSatelliteWithUserParameters() {
        String name = "ДЗЗ";
        double batteryLevel = 0.8;
        double resolution = 1.0;

        Satellite satellite = imgFactory.createSatelliteWithParameter(
            new ImagingSatelliteParam(name, batteryLevel, resolution)
        );

        assertNotNull(satellite);
        assertInstanceOf(ImagingSatellite.class, satellite);
        assertEquals(name, satellite.getName());
        assertEquals(batteryLevel, satellite.getEnergy().getBatteryLevel(), 0.001);

        ImagingSatellite imgSatellite = (ImagingSatellite) satellite;
        assertEquals(resolution, imgSatellite.getResolution(), 0.001);
    }
}
