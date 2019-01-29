package Car;

import java.awt.*;

public class AdHocCar extends Car {
    private static final Color COLOR = Color.red;

    public AdHocCar(String licensePlate, String brand) {
        super(120, licensePlate, brand);
        this.setHasToPay(true);
    }

    public Color getColor(){
        return COLOR;
    }
}
