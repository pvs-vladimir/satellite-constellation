package space;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@DisplayName("Integration-тест для ConstellationRepository")
class ConstellationRepositoryIntegrationTest {
    private static final String CONSTELLATION_1 = "testConstellation1";
    private static final String CONSTELLATION_2 = "testConstellation2";

    @Autowired
    private ConstellationRepository repository;

    @Test
    @DisplayName("Добавление нескольких группировок должно сохранять их все")
    void testAddConstellation() {
        SatelliteConstellation constellation1 = new SatelliteConstellation(CONSTELLATION_1);
        SatelliteConstellation constellation2 = new SatelliteConstellation(CONSTELLATION_2);

        repository.addConstellation(constellation1);
        repository.addConstellation(constellation2);

        assertTrue(repository.hasConstellation(CONSTELLATION_1));
        assertTrue(repository.hasConstellation(CONSTELLATION_2));
        assertEquals(2, repository.getAllConstellations().size());     
    }
}
