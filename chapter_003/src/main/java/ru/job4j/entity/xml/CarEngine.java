package ru.job4j.entity.xml;



public class CarEngine {

    private int engineId;
    private String engineType;

    public CarEngine() { }
    public CarEngine(String engineType) {
        this.setEngineType(engineType);
    }

    public int getEngineId() {
        return engineId;
    }

    public void setEngineId(int engineId) {
        this.engineId = engineId;
    }

    public String getEngineType() {
        return engineType;
    }

    public void setEngineType(String engineType) {
        this.engineType = engineType;
    }

    @Override
    public String toString() {
        return "CarEngine{"
                + "engineId=" + engineId
                + ", engineType='" + engineType + '\''
                + '}';
    }
}
