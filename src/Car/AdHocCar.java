package Car;

import java.util.Random;
import java.awt.*;

public class AdHocCar extends Car {
    private static final Color COLOR = Color.red;

    /**
     * Basic constructor for a Car.AdHocCar object with no parameters and set values.
     */
    public AdHocCar() {
        Random random = new Random();
        int stayMinutes = (int) (15 + random.nextFloat() * 3 * 60);
        this.setMinutesLeft(stayMinutes);
        this.setHasToPay(true);
    }

    /**
     * @param licensePlate a string containing a license plate-type.
     * @param brand        a string containing a brand-type.
     * @param minutesLeft  an integer determining how long a car should stay inside the garage.
     */
    public AdHocCar(String licensePlate, String brand, int minutesLeft) {
        super(licensePlate, brand, minutesLeft);
        Random random = new Random();
        int stayMinutes = minutesLeft;
        this.setMinutesLeft(stayMinutes);
        this.setHasToPay(true);
    }

    /**
     * @return the name of the color from the car in a string object.
     */
    public Color getColor(){
        return COLOR;
    }
}
