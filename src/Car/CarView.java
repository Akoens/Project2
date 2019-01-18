package Car;

import Simulation.Location;

import javax.swing.*;

public class CarView extends Box {

    private JLabel locationLabel;
    private JLabel isPayingLabel;
    private JLabel hasToPayLabel;

    public CarView() {
        super(BoxLayout.Y_AXIS);
        locationLabel = new JLabel();
        isPayingLabel = new JLabel();
        hasToPayLabel = new JLabel();
        add(locationLabel);
        add(isPayingLabel);
        add(hasToPayLabel);
    }

    public void update(Location location, boolean isPaying, boolean hasToPay) {
        isPayingLabel.setText(isPaying ? "This car is paying" : "This car is not paying");
        hasToPayLabel.setText(hasToPay ? "This car has not paid yet" : "This car does not have to pay");
        if (location != null) {
            locationLabel.setText("Floor " + location.getFloor() + ", Row " + location.getRow() + ", Place " + location.getPlace());
        }
    }

}
