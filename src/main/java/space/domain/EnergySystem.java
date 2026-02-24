package space.domain;

import lombok.Builder;

@Builder
public class EnergySystem {
    private double batteryLevel;

    private final double lowBatteryLevel;
    private final double minBatteryLevel;
    private final double maxBatteryLevel;

    // public EnergySystem(double batteryLevel) {
    //     this.batteryLevel = Double.max(Double.min(batteryLevel, MAX_BATTERY_LEVEL), MIN_BATTERY_LEVEL);
    // }

    public double getBatteryLevel() {
        return batteryLevel;
    }

    public boolean hasSufficientBatteryLevel() {
        return batteryLevel > lowBatteryLevel;
    }

    public void charge(double batteryAmount) {
        if (batteryAmount > 0.0) {
            batteryLevel = Double.min((batteryLevel + batteryAmount), maxBatteryLevel);
        }
    }

    public boolean consume(double batteryAmount) {
        boolean isEnough = false;
        if (batteryAmount > 0.0) {
            if (batteryLevel > batteryAmount) {
                isEnough = true;
            }
            batteryLevel = Double.max((batteryLevel - batteryAmount), minBatteryLevel);
        }
        return isEnough;
    }

    @Override
    public String toString() {
        return "EnergySystem{" +
               "batteryLevel=" + String.format("%.2f", getBatteryLevel()) + "}";
    }

}