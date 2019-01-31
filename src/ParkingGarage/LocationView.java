package ParkingGarage;

import Car.CarView;
import mdlaf.utils.MaterialColors;

import javax.swing.*;
import java.awt.*;

public class LocationView extends JPanel {

    private CarView carView;

    public LocationView() {
        setLayout(new BorderLayout());
        setMinimumSize(new Dimension(64, 32));

        carView = new CarView();
        add(carView, BorderLayout.CENTER);
    }

    public void updateView(Location location) {
        carView.updateView(location.getCar());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(MaterialColors.GRAY_500);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(MaterialColors.GRAY_700);
        g.fillRect(0, 0, 10, getHeight());
    }
}
