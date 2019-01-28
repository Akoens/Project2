package Car;

import java.awt.*;

public class ParkingPassCar extends Car {
    private static final Color COLOR = Color.blue;

    public ParkingPassCar(String licensePlate, String brand) {
        super(120, licensePlate, brand);
        this.setHasToPay(false);
    }

    public Color getColor(){
        return COLOR;
    }
}

