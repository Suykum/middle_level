package ru.job4j.models.xml;

public class Car {

    private int id;


    private String name;

    private CarBody body;

    private CarEngine engine;

    private CarTransmission transmission;

    public Car() { }

    public Car(String name) {
        this.setName(name);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CarBody getBody() {
        return body;
    }

    public void setBody(CarBody body) {
        this.body = body;
    }

    public CarEngine getEngine() {
        return engine;
    }

    public void setEngine(CarEngine engine) {
        this.engine = engine;
    }

    public CarTransmission getTransmission() {
        return transmission;
    }

    public void setTransmission(CarTransmission transmission) {
        this.transmission = transmission;
    }

    @Override
    public String toString() {
        return "Car{"
                + "id=" + id
                + ", name='" + name
                + '\'' + ", body=" + body
                + ", engine=" + engine
                + ", transmission=" + transmission
                + '}';
    }
}
