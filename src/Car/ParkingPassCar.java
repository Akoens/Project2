package Car;

import java.util.Random;
import java.awt.*;

public class ParkingPassCar extends Car {
    private static final Color COLOR = Color.blue;

    // TODO remove car non-parameter constructor
    public ParkingPassCar() {
        Random random = new Random();
        int stayMinutes = (int) (15 + random.nextFloat() * 3 * 60);
        this.setMinutesLeft(stayMinutes);
        this.setHasToPay(false);
    }

    public ParkingPassCar(String licensePlate, String brand) {
        super(licensePlate, brand);
        Random random = new Random();
        int stayMinutes = (int) (15 + random.nextFloat() * 3 * 60);
        this.setInitialMinutesLeft(stayMinutes);
        this.setMinutesLeft(stayMinutes);
        this.setHasToPay(false);
    }

    public Color getColor(){
        return COLOR;
    }
}

