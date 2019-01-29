package ParkingGarage;

import Car.Car;
import java.awt.*;

public class Location {

    public enum LocationType {
        DEFAULT,
        DISABLED,
        RECHARGE,
        RESERVED
    }

    private Car car;
    private LocationType locationType;

    public Location() {
        this(LocationType.DEFAULT);
    }

    public Location(LocationType locationType) {
        this.locationType = locationType;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Car getCar() {
        return car;
    }

    public boolean hasCar() {
        return car != null;
    }

    public LocationType getLocationType() {
        return locationType;
    }

    public Color getColor() {
        return Color.WHITE;
    }

}