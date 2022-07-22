package com.rentacar.demo.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Car {
    @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
    private int ID;
    private String name;
    private String image;
    private int price;
    private boolean active;

    public int getID() {
        return ID;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Car(int ID, String name, String image, int price, boolean active) {
        setID(ID);
        setName(name);
        setPrice(price);
        setActive(active);
        setImage(image);
    }

    public Car() {
    }
}
