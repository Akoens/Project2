package Car;

import java.util.Random;
import java.awt.*;

public class ParkingPassCar extends Car {
    private static final Color COLOR = Color.blue;

    public ParkingPassCar() {
        Random random = new Random();
        int stayMinutes = (int) (15 + random.nextFloat() * 3 * 60);
        this.setMinutesLeft(stayMinutes);
        this.setHasToPay(false);
    }

    public ParkingPassCar(String licensePlate, String brand, int minutesLeft) {
        super(licensePlate, brand, minutesLeft);
        Random random = new Random();
        int stayMinutes = minutesLeft;
        this.setMinutesLeft(stayMinutes);
        this.setHasToPay(false);
    }

    public Color getColor(){
        return COLOR;
    }
}

