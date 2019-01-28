package Car;

import ParkingGarage.Location;

import java.awt.*;

public abstract class Car {

    private String brand;
    private String licensePlate;
    private Location location;
    private int minutesLeft;
    private int initialMinutesLeft;
    private boolean isPaying;
    private boolean hasToPay;

    /**
     * Constructor for objects of class Car.Car
     */
    public Car(int minutesLeft, String licensePlate, String brand) {
        this.minutesLeft = minutesLeft;
        this.initialMinutesLeft = minutesLeft;
        this.licensePlate = licensePlate;
        this.brand = brand;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public int getInitialMinutesLeft() {
        return initialMinutesLeft;
    }


    public int getMinutesLeft() {
        return minutesLeft;
    }

    public void setMinutesLeft(int minutesLeft) {
        this.minutesLeft = minutesLeft;
    }

    public boolean getIsPaying() {
        return isPaying;
    }

    public void setIsPaying(boolean isPaying) {
        this.isPaying = isPaying;
    }

    public boolean getHasToPay() {
        return hasToPay;
    }

    public void setHasToPay(boolean hasToPay) {
        this.hasToPay = hasToPay;
    }

    public void tick() {
        minutesLeft--;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public abstract Color getColor();
}