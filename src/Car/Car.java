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
     *
     * @return an integer with how many minutes the car has left inside the garage.
     */
    public int getMinutesLeft() {
        return minutesLeft;
    }

    /**
     *
     * @param minutesLeft set car his amount of minutes left inside the garage.
     */
    public void setMinutesLeft(int minutesLeft) {
        this.minutesLeft = minutesLeft;
    }

    /**
     *
     * @return a boolean (true/false) whether the car is already paying the cost.
     */
    public boolean getIsPaying() {
        return isPaying;
    }

    /**
     *
     * @param isPaying set the boolean whether the car needs to pay or not.
     */
    public void setIsPaying(boolean isPaying) {
        this.isPaying = isPaying;
    }

    /**
     *
     * @return a boolean (true/false) If the car needs to pay.
     */
    public boolean getHasToPay() {
        return hasToPay;
    }

    /**
     *
     * @param hasToPay set the boolean whether the car needs to pay or not.
     */
    public void setHasToPay(boolean hasToPay) {
        this.hasToPay = hasToPay;
    }

    /**
     *
     * This method takes one minute off the minutesLeft variable, this way we cycle through ticks and decide if the car is going to leave.
     */
    public void tick() {
        minutesLeft--;
    }

    /**
     *
     * @return the car his brand in a string object.
     */
    public String getBrand() {
        return brand;
    }

    /**
     *
     * @return the initial minitesLeft
     */
    public int getInitialMinutesLeft() {
        return initialMinutesLeft;
    }

    /**
     *
     * @param brand sets the car his brand using the given string object.
     */
    public void setBrand(String brand) {
        this.brand = brand;
    }

    /**
     *
     * @return the car his license plate in a string object.
     */
    public String getLicensePlate() {
        return licensePlate;
    }

    /**
     *
     * @param licensePlate sets the car his license plate using the given string object.
     */
    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public abstract Color getColor();
}