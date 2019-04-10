package ru.job4j.car.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "car_id")
    private int id;

    @Column(name = "car_name")
    private String name;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "body_id")
    private CarBody body;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "engine_id")
    private CarEngine engine;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "transmission_id")
    private CarTransmission transmission;

    @Column(name = "price")
    private int price;

    @Column(name = "sold")
    private boolean sold;

    @Column(name = "location")
    private String location;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.REFRESH, CascadeType.MERGE})
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(fetch = FetchType.LAZY,
            mappedBy = "car",
            cascade = {CascadeType.ALL})
    private List<Image> images;

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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isSold() {
        return sold;
    }

    public void setSold(boolean sold) {
        this.sold = sold;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public void addImages(Image image) {
        if (images == null) {
            images = new ArrayList<>();
        }
        images.add(image);
    }

    @Override
    public String toString() {
        return "Car{"
                + "id=" + id
                + ", name='" + name
                + '\'' + ", body=" + body
                + ", engine=" + engine
                + ", transmission=" + transmission
                + ", price=" + price
                + ", sold=" + sold
                + ", location=" + location
                + ", user=" + user
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
        Car car = (Car) o;
        return id == car.id
                && price == car.price
                && sold == car.sold
                && Objects.equals(name, car.name)
                && Objects.equals(body, car.body)
                && Objects.equals(engine, car.engine)
                && Objects.equals(transmission, car.transmission)
                && Objects.equals(location, car.location)
                && Objects.equals(user, car.user)
                && Objects.equals(images, car.images);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, body, engine, transmission, price, sold, location, user, images);
    }
}
