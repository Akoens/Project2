package Car;

import UI.InterfaceContext;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class CarView extends JPanel {

    private InterfaceContext interfaceContext;

    private BufferedImage carImage;

    /**
     * Constructor for the CarView object, takes zero parameters.
     */
    public CarView() {
        interfaceContext = InterfaceContext.getInstance();
        setOpaque(false);
    }

    /**
     * Method that displays an images depending on the instance of the car.
     * @param car A car object
     */
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
        } else if (car instanceof DisabledCar) {
            carImage = interfaceContext.getDisabledCarImage();
        } else if (car instanceof ElectricCar) {
            carImage = interfaceContext.getElectricCarImage();
        }
    }

    /**
     * method that draws the car image to the parking garage view.
     * @param g details of the component that needs to be drawn.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int padding = 10;
        g.drawImage(carImage, padding / 2 + 4, padding / 2, getWidth() - padding, getHeight() - padding, null);
    }
}
