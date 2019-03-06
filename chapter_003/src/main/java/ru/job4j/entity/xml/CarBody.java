package ru.job4j.entity.xml;

public class CarBody {

    private int bodyId;

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
