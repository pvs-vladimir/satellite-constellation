package space.domain;

import space.constants.CommunicationSatelliteConstants;

public class CommunicationSatellite extends Satellite {
    private final double bandwidth;
    private final double sendEnergyConsumption;

    public CommunicationSatellite(String name, double batteryLevel, double bandwidth) {
        super(name, batteryLevel);
        this.bandwidth = bandwidth;
        this.sendEnergyConsumption = CommunicationSatelliteConstants.SEND_ENERGY_CONSUMPTION;
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
            useEnergy(sendEnergyConsumption);
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