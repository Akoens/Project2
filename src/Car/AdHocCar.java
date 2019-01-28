package Car;

import java.util.Random;
import java.awt.*;

public class AdHocCar extends Car {
    private static final Color COLOR = Color.red;

    // TODO remove car non-parameter constructor
    public AdHocCar() {
        Random random = new Random();
        int stayMinutes = (int) (15 + random.nextFloat() * 3 * 60);
        this.setMinutesLeft(stayMinutes);
        this.setHasToPay(true);
    }

    public AdHocCar(String licensePlate, String brand) {
        super(licensePlate, brand);
        Random random = new Random();
        int stayMinutes = (int) (15 + random.nextFloat() * 3 * 60);
        this.setInitialMinutesLeft(stayMinutes);
        this.setMinutesLeft(stayMinutes);
        this.setHasToPay(true);
    }

    public Color getColor(){
        return COLOR;
    }
}
