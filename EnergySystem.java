public class EnergySystem {
    private double batteryLevel;
    private final double minActiveBatteryLevel = 0.2;

    public EnergySystem(double batteryLevel) {
        this.batteryLevel = Double.max(Double.min(batteryLevel, 1.0), 0.0);
    }

    public double getBatteryLevel() {
        return batteryLevel;
    }

    public boolean hasMinActiveBatteryLevel() {
        return batteryLevel > minActiveBatteryLevel;
    }

    public void charge(double batteryAmount) {
        if (batteryAmount > 0.0) {
            batteryLevel = Double.min((batteryLevel + batteryAmount), 1.0);
        }
    }

    public void consume(double batteryAmount, SatelliteState state) {
        if (batteryAmount > 0.0) {
            batteryLevel = Double.max((batteryLevel - batteryAmount), 0.0);
        }

        if (!hasMinActiveBatteryLevel()) {
            state.deactivate();
        }
    }

}