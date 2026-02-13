package space;

import java.util.ArrayList;
import java.util.List;

public class SatelliteConstellation {
    private final String constellationName;
    private final List<Satellite> satellites;

    public SatelliteConstellation(String constellationName) {
        this.constellationName = constellationName;
        this.satellites = new ArrayList<>();
        System.out.println(String.format("Создана спутниковая группировка: %s",
                                         this.constellationName));
    }

    public String getConstellationName() {
        return constellationName;
    }

    public List<Satellite> getSatellites() {
        return satellites;
    }

    public void addSatellite(Satellite satellite) {
        if (satellite != null && !satellites.contains(satellite)) {
            satellites.add(satellite);
            System.out.println(String.format("%s добавлен в группировку '%s'",
                                            satellite.getName(), constellationName));
        }
    }

    public void executeAllMissions() {
        for (Satellite satellite : satellites) {
            satellite.performMission();
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append('[');
        boolean isFirst = true;
        for (Satellite satellite : satellites) {
            if (!isFirst) {
                sb.append(",\n");
            }
            sb.append(satellite.toString());
            isFirst = false;
        }
        sb.append(']');

        return sb.toString();
    }
    
}