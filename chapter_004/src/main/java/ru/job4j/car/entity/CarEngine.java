package ru.job4j.car.entity;

import javax.persistence.*;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CarEngine carEngine = (CarEngine) o;
        return engineId == carEngine.engineId
                && Objects.equals(engineType, carEngine.engineType);
    }

    @Override
    public int hashCode() {

        return Objects.hash(engineId, engineType);
    }
}
