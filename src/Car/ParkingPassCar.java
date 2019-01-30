package Car;

import java.awt.*;

public class ParkingPassCar extends Car {
    private static final Color COLOR = Color.blue;

    /**
     * @param licensePlate a string containing a license plate-type.
     * @param brand        a string containing a brand-type.
     * @param minutesLeft  an integer determining how long a car should stay inside the garage.
     */
    public ParkingPassCar(String licensePlate, String brand, int minutesLeft) {
        super(licensePlate, brand, minutesLeft, false);
    }

    /**
     * @return the name of the color from the car in a string object.
     */
    public Color getColor(){
        return COLOR;
    }
}

