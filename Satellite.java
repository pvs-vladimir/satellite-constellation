public abstract class Satellite {
    protected String name;
    protected boolean isActive;
    protected double batteryLevel;
    private final double minActivationBatteryLevel = 0.2;

    public Satellite(String name, double batteryLevel) {
        this.name = name;
        this.isActive = false;
        this.batteryLevel = batteryLevel;

        System.out.println(String.format("Создан спутник: %s (заряд: %d%%)",
                                         this.name, (int) (this.batteryLevel * 100)));
    }

    public String getName() {
        return name;
    }

    public boolean activate() {
        if (!isActive && batteryLevel > minActivationBatteryLevel) {
            isActive = true;

            System.out.println(String.format("✅ %s: Активация успешна", name));
            return isActive;
        }

        System.out.println(String.format("❌ %s: Ошибка активации (заряд: %d%%)",
                                         name, (int) (this.batteryLevel * 100)));
        return isActive;
    }

    public void deactivate() {
        if (isActive) {
            isActive = false;
        }
    }

    public void consumeBattery(double batteryAmount) {
        if (batteryAmount <= 0) return;

        batteryLevel = Double.max((batteryLevel - batteryAmount), 0);
        if (batteryLevel < minActivationBatteryLevel) {
            deactivate();
        }
    }

    protected abstract void performMission();
}