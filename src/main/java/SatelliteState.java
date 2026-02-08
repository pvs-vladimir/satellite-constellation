public class SatelliteState {
    private boolean isActive = false;
    private String statusMessage;

    public SatelliteState() {
        this.statusMessage = "Не активен";
    }

    public boolean isActive() {
        return isActive;
    }

    public String getStatus() {
        return statusMessage;
    }

    public boolean activate(boolean hasSufficientBatteryLevel) {
        if (!isActive && hasSufficientBatteryLevel) {
            isActive = true;
            statusMessage = "Активен";
            return true;
        }
        statusMessage = hasSufficientBatteryLevel ? "Уже активен" : "Недостаточно энергии";
        return false;
    }

    public void deactivate() {
        if (isActive) {
            isActive = false;
            statusMessage = "Деактивирован";
        }
    }

    @Override
    public String toString() {
        return "SatelliteState{" +
               "isActive=" + isActive + ", " +
               "statusMessage=" + statusMessage + "}";
    }

}