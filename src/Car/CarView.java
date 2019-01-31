package Car;

import UI.InterfaceContext;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class CarView extends JPanel {

    private InterfaceContext interfaceContext;

    private int padding = 10;
    private BufferedImage carImage;

    public CarView() {
        interfaceContext = InterfaceContext.getInstance();
        setOpaque(false);
    }

    public void updateView(Car car) {
        if (car == null) {
            carImage = null;
            return;
        }

        if (car instanceof AdHocCar) {
            carImage = interfaceContext.getAdHocCarImage();
        } else if (car instanceof ParkingPassCar) {
            carImage = interfaceContext.getParkingPassCarImage();
        } else if (car instanceof ReservationCar) {
            carImage = interfaceContext.getReservationCarImage();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(carImage, padding/2 + 4, padding/2, getWidth()-padding, getHeight()-padding, null);
    }
}
