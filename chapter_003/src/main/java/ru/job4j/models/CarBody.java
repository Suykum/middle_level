package ru.job4j.models;

import javax.persistence.*;

@Entity
@Table(name = "body")
public class CarBody {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "body_id")
    private int bodyId;

    @Column(name = "body_type")
    private String bodyType;

    public CarBody() { }

    public CarBody(String bodyType) {
        this.setBodyType(bodyType);
    }

    public int getBodyId() {
        return bodyId;
    }

    public void setBodyId(int bodyId) {
        this.bodyId = bodyId;
    }

    public String getBodyType() {
        return bodyType;
    }

    public void setBodyType(String bodyType) {
        this.bodyType = bodyType;
    }

    @Override
    public String toString() {
        return "CarBody{"
                + "bodyId=" + bodyId
                + ", bodyType='" + bodyType + '\''
                + '}';
    }
}
