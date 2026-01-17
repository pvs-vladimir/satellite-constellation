public abstract class Satellite {
    protected String name;
    protected SatelliteState state;
    protected EnergySystem energy;

    public Satellite(String name, double batteryLevel) {
        this.name = name;
        this.state = new SatelliteState();
        this.energy = new EnergySystem(batteryLevel);

        System.out.println(String.format("Создан спутник: %s (заряд: %d%%)",
                                         this.name, (int) (this.energy.getBatteryLevel() * 100)));
    }

    public String getName() {
        return name;
    }

    public boolean isActive() {
        return state.isActive();
    }

    public double getBatteryLevel() {
        return energy.getBatteryLevel();
    }

    public boolean activate() {
        boolean res = state.activate(energy);
        if (res) {
            System.out.println(String.format("✅ %s: Активация успешна", name));
        } else {
            System.out.println(String.format("❌ %s: Ошибка активации (заряд: %d%%)",
                                             name, (int) (energy.getBatteryLevel() * 100)));
        }
        return res;
    }

    public void deactivate() {
        state.deactivate();
    }

    public void consumeBattery(double batteryAmount) {
        energy.consume(batteryAmount, state);
    }

    protected abstract void performMission();
}