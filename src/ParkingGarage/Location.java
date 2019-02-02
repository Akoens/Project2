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

    /**
     * Default constructor for the Location object taking zero parameters.
     */
    public Location() {
        this(LocationType.DEFAULT);
    }

    /**
     * Secondary constructor for the Location object taking one parameter.
     *
     * @param locationType one of the available enum's declared: DEFAULT, DISABLED, RECHARGE, or RESERVED.
     */
    public Location(LocationType locationType) {
        this.locationType = locationType;
    }

    /**
     * Method to set a specific car to a specific location.
     * @param car a car object.
     */
    public void setCar(Car car) {
        this.car = car;
    }

    /**
     * Method to return a specific car that's parked on that location.
     * @return
     */
    public Car getCar() {
        return car;
    }

    /**
     * Method to check if a location is already in use by a car.
     * @return
     */
    public boolean hasCar() {
        return car != null;
    }


    /**
     * Method to see which kind of location(enum)-type this location is.
     * @return the corresponding location(enum)-type.
     */
    public void setLocationType(LocationType locationType) {
        this.locationType = locationType;
    }

    public LocationType getLocationType() {
        return locationType;
    }

    /**
     * Method to check which color this location has.
     * @return a color object.
     */
    public Color getColor() {
        return Color.WHITE;
    }

}