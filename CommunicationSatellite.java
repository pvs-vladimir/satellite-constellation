public class CommunicationSatellite extends Satellite {
    private final double bandwidth;

    public CommunicationSatellite(String name, double batteryLevel, double bandwidth) {
        super(name, batteryLevel);
        this.bandwidth = bandwidth;
    }

    public double getBandwidth() {
        return bandwidth;
    }

    @Override
    public void performMission() {
        if (isActive) {
            System.out.println(String.format("✅ %s: Передача данных со скоростью %.1f Мбит/с",
                                             name, bandwidth));
            sendData(bandwidth);
            consumeBattery(0.05);
        } else {
            System.out.println(String.format("❌ %s: Не может выполнить передачу данных - не активен", name));
        }
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "{" +
               "bandwidth=" + bandwidth + ", " +
               "name='" + name + "', " +
               "isActive=" + isActive + ", " +
               "batteryLevel=" + batteryLevel + "}";
    }

    private void sendData(double dataAmount) {
        if (isActive) {
            System.out.println(String.format("✅ %s: Отправил %.1f Мбит данных!", name, dataAmount));
        }
    }
  
}