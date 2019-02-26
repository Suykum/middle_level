package ru.job4j.models;

import javax.persistence.*;

@Entity
@Table(name = "cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "car_id")
    private int id;

    @Column(name = "car_name")
    private String name;

    @OneToOne
    @JoinColumn(name = "body_id")
    private CarBody body;

    @OneToOne
    @JoinColumn(name = "engine_id")
    private CarEngine engine;

    @OneToOne
    @JoinColumn(name = "transmission_id")
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
