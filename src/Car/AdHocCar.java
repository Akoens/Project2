package Car;

import java.awt.*;

public class AdHocCar extends Car {
    public static final Color COLOR = Color.red;

    /**
     * Constructor for the car, three parameters
     * @param licensePlate a string containing a license plate-type.
     * @param brand        a string containing a brand-type.
     * @param minutesLeft  an integer determining how long a car should stay inside the garage.
     */
    public AdHocCar(String licensePlate, String brand, int minutesLeft) {
        super(licensePlate, brand, minutesLeft, true);
    }

    /**
     * @return the name of the color from the car in a string object.
     */
    public Color getColor(){
        return COLOR;
    }
}
