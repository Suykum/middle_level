package ru.job4j.models;

import javax.persistence.*;

@Entity
@Table(name = "engine")
public class CarEngine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "engine_id")
    private int engineId;

    @Column(name = "engine_type")
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
