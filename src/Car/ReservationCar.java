package Car;

import java.awt.*;
import java.util.Random;

public class ReservationCar extends Car{
    private static final Color COLOR = Color.yellow;

    public ReservationCar(String licensePlate, String brand, int minutesLeft) {
        super(licensePlate, brand, minutesLeft, true);
    }

    public Color getColor(){
        return COLOR;
    }
}
