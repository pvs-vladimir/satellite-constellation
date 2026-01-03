public class ImagingSatellite extends Satellite {
    private final double resolution;
    private int photosTaken;

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
        takePhoto();
        if (isActive) {
            consumeBattery(0.08);
        }
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "{" +
               "resolution=" + resolution + ", " +
               "photosTaken=" + photosTaken + ", " +
               "name='" + name + "', " +
               "isActive=" + isActive + ", " +
               "batteryLevel=" + batteryLevel + "}";
    }

    private void takePhoto() {
        if (isActive) {
            photosTaken++;

            System.out.println(String.format("✅ %s: Съемка территории с разрешением %.1f м/пиксель",
                                             name, resolution));
            System.out.println(String.format("✅ %s: Снимок #%d сделан!", name, photosTaken));
            return;
        }

        System.out.println(String.format("❌ %s: Не может выполнить съемку - не активен", name));
    }
}