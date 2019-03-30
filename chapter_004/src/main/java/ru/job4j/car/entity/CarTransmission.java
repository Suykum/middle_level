package ru.job4j.car.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "transmission")
public class CarTransmission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transmission_id")
    private int transmissionId;

    @Column(name = "transmission_type")
    private String transmissionType;

    public CarTransmission() { }

    public CarTransmission(String transmissionType) {
        this.setTransmissionType(transmissionType);
    }

    public int getTransmissionId() {
        return transmissionId;
    }

    public void setTransmissionId(int transmissionId) {
        this.transmissionId = transmissionId;
    }

    public String getTransmissionType() {
        return transmissionType;
    }

    public void setTransmissionType(String transmissionType) {
        this.transmissionType = transmissionType;
    }

    @Override
    public String toString() {
        return "CarTransmission{"
                + "transmission_id=" + transmissionId
                + ", transmissionType='" + transmissionType + '\''
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
        CarTransmission that = (CarTransmission) o;
        return transmissionId == that.transmissionId
                && Objects.equals(transmissionType, that.transmissionType);
    }

    @Override
    public int hashCode() {

        return Objects.hash(transmissionId, transmissionType);
    }
}
