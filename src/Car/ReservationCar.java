package Car;

import java.awt.*;
import java.util.Random;

public class ReservationCar extends Car{
    private static final Color COLOR = Color.yellow;

    // TODO remove car non-parameter constructor
    public ReservationCar() {
        Random random = new Random();
        int stayMinutes = (int) (15 + random.nextFloat() * 3 * 60);
        this.setMinutesLeft(stayMinutes);
        this.setHasToPay(true);
    }

    public ReservationCar(String licensePlate, String brand) {
        super(licensePlate, brand);
        Random random = new Random();
        int stayMinutes = (int) (15 + random.nextFloat() * 3 * 60);
        int patience = (int) (15 + random.nextFloat() * 5 * 60);
        this.setPatience(patience);
        this.setInitialMinutesLeft(stayMinutes);
        this.setMinutesLeft(stayMinutes);
        this.setHasToPay(true);
    }

    public Color getColor(){
        return COLOR;
    }
}
