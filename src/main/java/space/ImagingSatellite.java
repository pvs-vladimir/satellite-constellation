package space;

public class ImagingSatellite extends Satellite {
    private final double resolution;
    private int photosTaken;
    private final double photoEnergyConsumption = 0.08;

    public ImagingSatellite(String name, double batteryLevel, double resolution) {
        super(name, batteryLevel);
        this.resolution = resolution;
        this.photosTaken = 0;
    }

    public double getResolution() {
        return resolution;
    }

    public int getPhotosTaken() {
        return photosTaken;
    }

    @Override
    public void performMission() {
        if (state.isActive()) {
            System.out.println(String.format("✅ %s: Съемка территории с разрешением %.1f м/пиксель",
                                             name, resolution));
            takePhoto();
            useEnergy(photoEnergyConsumption);
        } else {
            System.out.println(String.format("❌ %s: Не может выполнить съемку - не активен", name));
        }
    }

    @Override
    public String toString() {
        return "ImagingSatellite{" +
               "resolution=" + resolution + ", " +
               "photosTaken=" + photosTaken + ", " +
               "name='" + name + "', " +
               "state=" + state + ", " +
               "energy=" + energy + "}";
    }

    private void takePhoto() {
        if (state.isActive()) {
            photosTaken++;
            System.out.println(String.format("✅ %s: Снимок #%d сделан!", name, photosTaken));
        }
    }
}