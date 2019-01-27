package Car;

import java.awt.*;
import java.util.Random;

public class ReservationCar extends Car{
    private static final Color COLOR = Color.yellow;

    public ReservationCar() {
        Random random = new Random();
        int stayMinutes = (int) (15 + random.nextFloat() * 3 * 60);
        this.setMinutesLeft(stayMinutes);
        this.setHasToPay(true);
    }

    public ReservationCar(String licensePlate, String brand, int minutesLeft) {
        super(licensePlate, brand, minutesLeft);
        Random random = new Random();
        int stayMinutes = minutesLeft;
        this.setMinutesLeft(stayMinutes);
        this.setHasToPay(true);
    }

    public Color getColor(){
        return COLOR;
    }
}
