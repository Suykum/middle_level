package ru.job4j.entity;

import jdk.nashorn.internal.ir.annotations.Ignore;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Base64;

@Entity
@Table(name = "image")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id")
    private int id;

    @Column(name = "image")
    private byte[] image;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.REFRESH, CascadeType.MERGE})
    @JoinColumn(name = "car_id")
    private Car car;

    @Transient
    private String base64Image;

    public Image() { }

    public Image(byte[] image) {
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }


    public String getBase64Image() {
        base64Image = Base64.getEncoder().encodeToString(this.image);
        return base64Image;
    }

    public void setBase64Image(String base64Image) {
        this.base64Image = base64Image;
    }

    @Override
    public String toString() {
        return "Image{"
                + "id=" + id
                + ", image=" + Arrays.toString(image)
                + '}';
    }
}
