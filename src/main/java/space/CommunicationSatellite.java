package space;

public class CommunicationSatellite extends Satellite {
    private final double bandwidth;
    private final double sendBatteryConsumption = 0.05;

    public CommunicationSatellite(String name, double batteryLevel, double bandwidth) {
        super(name, batteryLevel);
        this.bandwidth = bandwidth;
    }

    public double getBandwidth() {
        return bandwidth;
    }

    @Override
    public void performMission() {
        if (state.isActive()) {
            System.out.println(String.format("✅ %s: Передача данных со скоростью %.1f Мбит/с",
                                             name, bandwidth));
            sendData(bandwidth);
            useEnergy(sendBatteryConsumption);
        } else {
            System.out.println(String.format("❌ %s: Не может выполнить передачу данных - не активен", name));
        }
    }

    @Override
    public String toString() {
        return "CommunicationSatellite{" +
               "bandwidth=" + bandwidth + ", " +
               "name='" + name + "', " +
               "state=" + state + ", " +
               "energy=" + energy + "}";
    }

    private void sendData(double dataAmount) {
        if (state.isActive()) {
            System.out.println(String.format("✅ %s: Отправил %.1f Мбит данных!", name, dataAmount));
        }
    }
  
}