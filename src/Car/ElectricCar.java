package Car;

import java.awt.*;

public class ElectricCar extends Car {
    public static final Color COLOR = Color.green;

    public ElectricCar(String licensePlate, String brand, int minutesLeft) {
        super(licensePlate, brand, minutesLeft, true);
    }

    public Color getColor() {
        return COLOR;
    }
}
