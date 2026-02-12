public class EnergySystem {
    private double batteryLevel;

    private final double LOW_BATTERY_LEVEL = 0.2;
    private final double MIN_BATTERY_LEVEL = 0.0;
    private final double MAX_BATTERY_LEVEL = 1.0;

    public EnergySystem(double batteryLevel) {
        this.batteryLevel = Double.max(Double.min(batteryLevel, MAX_BATTERY_LEVEL), MIN_BATTERY_LEVEL);
    }

    public double getBatteryLevel() {
        return batteryLevel;
    }

    public boolean hasSufficientBatteryLevel() {
        return batteryLevel > LOW_BATTERY_LEVEL;
    }

    public void charge(double batteryAmount) {
        if (batteryAmount > 0.0) {
            batteryLevel = Double.min((batteryLevel + batteryAmount), MAX_BATTERY_LEVEL);
        }
    }

    public boolean consume(double batteryAmount) {
        boolean isEnough = false;
        if (batteryAmount > 0.0) {
            if (batteryLevel > batteryAmount) {
                isEnough = true;
            }
            batteryLevel = Double.max((batteryLevel - batteryAmount), MIN_BATTERY_LEVEL);
        }
        return isEnough;
    }

    @Override
    public String toString() {
        return "EnergySystem{" +
               "batteryLevel=" + String.format("%.2f", getBatteryLevel()) + "}";
    }

}