package Car;

import java.awt.*;

public class DisabledCar extends Car {
    private static final Color COLOR = Color.blue;

    public DisabledCar(String licensePlate, String brand, int minutesLeft) {
        super(licensePlate, brand, minutesLeft, true);
    }

    public Color getColor() {
        return COLOR;
    }
}
