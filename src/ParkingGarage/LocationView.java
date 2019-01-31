package ParkingGarage;

import Car.CarView;
import Car.DisabledCar;
import Car.ElectricCar;
import Car.ReservationCar;
import mdlaf.utils.MaterialColors;

import javax.swing.*;
import java.awt.*;

public class LocationView extends JPanel {

    private Color barColor;
    private CarView carView;

    public LocationView() {
        setLayout(new BorderLayout());
        setMinimumSize(new Dimension(64, 32));

        carView = new CarView();
        add(carView, BorderLayout.CENTER);
    }

    public void updateView(Location location) {
        switch (location.getLocationType()) {
            case DEFAULT:
                barColor = MaterialColors.GRAY_700;
                break;
            case DISABLED:
                barColor = DisabledCar.COLOR;
                break;
            case RECHARGE:
                barColor = ElectricCar.COLOR;
                break;
            case RESERVED:
                barColor = ReservationCar.COLOR;
                break;
        }
        carView.updateView(location.getCar());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(MaterialColors.GRAY_500);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(barColor);
        g.fillRect(0, 0, 10, getHeight());
    }
}
