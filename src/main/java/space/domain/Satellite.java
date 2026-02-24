package space.domain;

import space.constants.EnergySystemConstants;

public abstract class Satellite {
    protected String name;
    protected SatelliteState state;
    protected EnergySystem energy;

    public Satellite(String name, double batteryLevel) {
        this.name = name;
        this.state = new SatelliteState();
        this.energy = EnergySystem.builder()
                        .batteryLevel(batteryLevel)
                        .lowBatteryLevel(EnergySystemConstants.LOW_BATTERY_LEVEL)
                        .minBatteryLevel(EnergySystemConstants.MIN_BATTERY_LEVEL)
                        .maxBatteryLevel(EnergySystemConstants.MAX_BATTERY_LEVEL)
                        .build();

        System.out.println(String.format("Создан спутник: %s (заряд: %d%%)",
                                         this.name, (int) (this.energy.getBatteryLevel() * 100)));
    }

    public String getName() {
        return name;
    }

    public SatelliteState getState() {
        return state;
    }

    public EnergySystem getEnergy() {
        return energy;
    }

    public boolean activate() {
        boolean res = state.activate(energy.hasSufficientBatteryLevel());
        if (res) {
            System.out.println(String.format("✅ %s: Активация успешна", name));
        } else {
            System.out.println(String.format("❌ %s: Ошибка активации (заряд: %d%%)",
                                             name, (int) (energy.getBatteryLevel() * 100)));
        }
        return res;
    }

    public void deactivate() {
        if (state.isActive()) {
            state.deactivate();
            System.out.println(String.format("❌ %s: Деактивирован", name));
        }
    }

    protected abstract void performMission();

    protected void useEnergy(double batteryAmount) {
        energy.consume(batteryAmount);
        if (!energy.hasSufficientBatteryLevel()) {
            deactivate();
        }
    }
}