package Car;

import java.awt.*;

public abstract class Car {

    private String brand;
    private String licensePlate;
    private int minutesLeft;
    private int initialMinutesLeft;
    private boolean isPaying;
    private boolean hasToPay;

    /**
     * Constructor for the car, four parameters
     * @param licensePlate a string containing a license plate-type.
     * @param brand        a string containing a brand-type.
     * @param minutesLeft  an integer determining how long a car should stay inside the garage.
     * @param hasToPay set the boolean whether the car needs to pay or not.
     */
    public Car(String licensePlate, String brand, int minutesLeft, boolean hasToPay) {
        this.licensePlate = licensePlate;
        this.brand = brand;
        this.minutesLeft = minutesLeft;
        this.initialMinutesLeft = minutesLeft;
        this.hasToPay = hasToPay;
    }

    /**
     * Method that uses the color of the car to determine the type of car
     * @return String Containing the type of car
     */
    public String getTypeCarByColor() {
        if (this.getColor() == Color.GREEN) {
            return "Electric";
        }

        if (this.getColor() == Color.BLUE) {
            return "Disabled";
        }

        if (this.getColor() == Color.ORANGE) {
            return "Parking Pass";
        }

        if (this.getColor() == Color.YELLOW) {
            return "Reservation";
        }

        if (this.getColor() == Color.RED) {
            return "Normal";
        }
        return "Error";
    }

    /**
     * @return an integer with how many minutes the car has left inside the garage.
     */
    public int getMinutesLeft() {
        return minutesLeft;
    }

    /**
     * @return a boolean (true/false) whether the car is already paying the cost.
     */
    public boolean getIsPaying() {
        return isPaying;
    }

    /**
     * @param isPaying set the boolean whether the car needs to pay or not.
     */
    public void setIsPaying(boolean isPaying) {
        this.isPaying = isPaying;
    }

    /**
     * @return a boolean (true/false) If the car needs to pay.
     */
    public boolean getHasToPay() {
        return hasToPay;
    }

    /**
     * @param hasToPay set the boolean whether the car needs to pay or not.
     */
    public void setHasToPay(boolean hasToPay) {
        this.hasToPay = hasToPay;
    }

    /**
     * This method takes one minute off the minutesLeft variable in order to decide when a car will leave.
     */
    public void tick() {
        minutesLeft--;
    }

    /**
     * @return the car his brand in a string object.
     */
    public String getBrand() {
        return brand;
    }

    /**
     * @return the initial minutesLeft of how long a car stays in the garage.
     */
    public int getInitialMinutesLeft() {
        return initialMinutesLeft;
    }

    /**
     * @return the car his license plate in a string object.
     */
    public String getLicensePlate() {
        return licensePlate;
    }

    /**
     * Set how long the car was originally in the garage, has impact on price.
     * @param initialMinutesLeft is the new amount of minutes left of that car in the garage.
     */
    public void setInitialMinutesLeft(int initialMinutesLeft) {
        this.initialMinutesLeft = initialMinutesLeft;
    }

    /**
     * abstract method that gets the color
     */
    public abstract Color getColor();
}