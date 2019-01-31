package UI;

import Application.ApplicationState;
import Car.Car;
import ParkingGarage.ParkingGarageSimulatorListener;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;

public class HeaderPanel extends JPanel implements ParkingGarageSimulatorListener {

    private ApplicationState applicationState;

    private SimpleDateFormat dateFormat;
    private JLabel dateLabel;

    public HeaderPanel() {
        applicationState = ApplicationState.getInstance();
        applicationState.getParkingGarageSimulator().addParkingGarageSimulatorListener(this);
        dateFormat = new SimpleDateFormat("EEEE dd/MM/yyyy HH:mm:ss");

        setMinimumSize(new Dimension(100, 64));
        setBorder(Theme.getDefaultBorder());
        setLayout(new BorderLayout());

        dateLabel = new JLabel("", SwingConstants.CENTER);
        add(dateLabel, BorderLayout.CENTER);

        setVisible(true);
    }

    @Override
    public void onTick() {
        dateLabel.setText(dateFormat.format(applicationState.getParkingGarageSimulator().getDate()));
    }

    @Override
    public void onCarEnter(Car car) {
    }

    @Override
    public void onCarExit(Car car) {
    }

    @Override
    public void onCarPayment(Car car, double revenue) {
    }
}
