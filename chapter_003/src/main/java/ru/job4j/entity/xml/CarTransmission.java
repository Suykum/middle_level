package ru.job4j.entity.xml;

public class CarTransmission {

    private int transmissionId;

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
}
