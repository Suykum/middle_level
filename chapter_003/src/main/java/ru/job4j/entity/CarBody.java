package ru.job4j.entity;

import javax.persistence.*;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CarBody body = (CarBody) o;
        return bodyId == body.bodyId
                && Objects.equals(bodyType, body.bodyType);
    }

    @Override
    public int hashCode() {

        return Objects.hash(bodyId, bodyType);
    }
}
