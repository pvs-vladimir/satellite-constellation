package space;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public class ConstellationRepository {
    private final Map<String, SatelliteConstellation> constellations;

    public ConstellationRepository() {
        this.constellations = new HashMap<String, SatelliteConstellation>();
    }

    public Collection<SatelliteConstellation> getAllConstellations() {
        return constellations.values();
    }

    public void addConstellation(SatelliteConstellation constellation) {
        if (!hasConstellation(constellation.getConstellationName())) {
            constellations.put(constellation.getConstellationName(), constellation);
            System.out.println("Сохранена группировка: " + constellation.getConstellationName());
        }
    }

    public SatelliteConstellation getConstellation(String constellationName) {
        SatelliteConstellation constellation = constellations.get(constellationName);
        if (constellation == null) {
            throw new RuntimeException("Группировка не найдена: " + constellationName);
        }
        return constellation;
    }

    public boolean hasConstellation(String constellationName) {
        return constellations.containsKey(constellationName);
    }

    public void removeConstellation(String constellationName) {
        constellations.remove(constellationName);
        System.out.println("Удалена группировка: " + constellationName);
    }
}