public class SatelliteState {
    private boolean isActive = false;

    public boolean isActive() {
        return isActive;
    }

    public boolean activate(EnergySystem energy) {
        if (!isActive && energy.hasMinActiveBatteryLevel()) {
            isActive = true;
        }
        return isActive;
    }

    public void deactivate() {
        if (isActive) {
            isActive = false;
        }
    }

}