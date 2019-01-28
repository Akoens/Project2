package Car;

import java.awt.*;

public class ReservationCar extends Car{
    private static final Color COLOR = Color.yellow;

    public ReservationCar(String licensePlate, String brand) {
        super(120, licensePlate, brand);
        this.setHasToPay(true);
    }

    public Color getColor(){
        return COLOR;
    }
}
