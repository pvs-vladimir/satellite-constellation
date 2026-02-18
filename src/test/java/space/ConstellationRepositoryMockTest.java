package space;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@DisplayName("Mock-тест для ConstellationRepository")
class ConstellationRepositoryMockTest {
    private static final String CONSTELLATION_1 = "testConstellation1";
    private static final String CONSTELLATION_2 = "testConstellation2";

    @Mock
    private ConstellationRepository repository;

    @Test
    @DisplayName("Добавление нескольких группировок должно сохранять их все")
    void testAddConstellation() {
        SatelliteConstellation constellation1 = new SatelliteConstellation(CONSTELLATION_1);
        SatelliteConstellation constellation2 = new SatelliteConstellation(CONSTELLATION_2);

        Map<String, SatelliteConstellation> constellations = Map.of(
            CONSTELLATION_1, constellation1,
            CONSTELLATION_2, constellation2);

        when(repository.hasConstellation(CONSTELLATION_1)).thenReturn(true);
        when(repository.hasConstellation(CONSTELLATION_2)).thenReturn(true);

        when(repository.getAllConstellations()).thenReturn(constellations);

        assertTrue(repository.hasConstellation(CONSTELLATION_1));
        assertTrue(repository.hasConstellation(CONSTELLATION_2));
        assertEquals(2, repository.getAllConstellations().size());   
    }
}
