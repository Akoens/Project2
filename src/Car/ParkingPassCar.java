package Car;

import java.awt.*;

public class ParkingPassCar extends Car {
    public static final Color COLOR = Color.orange;

    public ParkingPassCar(String licensePlate, String brand, int minutesLeft) {
        super(licensePlate, brand, minutesLeft, false);
    }

    public Color getColor(){
        return COLOR;
    }
}

