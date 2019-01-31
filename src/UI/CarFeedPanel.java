package UI;

import Application.ApplicationState;
import Car.Car;
import ParkingGarage.ParkingGarageSimulatorListener;
import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.Queue;

public class CarFeedPanel extends JPanel implements ParkingGarageSimulatorListener {

    private static final int MAX_SIZE = 100;

    private JPanel scrollContent;
    private JScrollPane scrollPane;
    private Queue<JLabel> brandLabelQue;
    private Queue<JLabel> licenseLabelQue;
    private ApplicationState applicationState;

    public CarFeedPanel() {
        applicationState = ApplicationState.getInstance();
        applicationState.getParkingGarageSimulator().addParkingGarageSimulatorListener(this);

        setMinimumSize(new Dimension(100, 64));
        setBorder(Theme.getDefaultBorder());
        setLayout (new BorderLayout());
        licenseLabelQue = new LinkedList<>();
        brandLabelQue = new LinkedList<>();

        scrollContent = new JPanel(new GridLayout(0, 2));
        scrollPane = new JScrollPane(scrollContent);
        add(new JLabel("New arrivals"), BorderLayout.PAGE_START);
        add(scrollPane, BorderLayout.CENTER);

        setVisible(true);
    }

    @Override
    public void onTick() {
    }

    @Override
    public void onCarEnter(Car car) {
        if (scrollContent.getComponentCount() > MAX_SIZE && licenseLabelQue.peek() != null && brandLabelQue.peek() != null) {
            scrollContent.remove(brandLabelQue.poll());
            scrollContent.remove(licenseLabelQue.poll());
        }
        JLabel licenseLabel = new JLabel(car.getLicensePlate());
        JLabel brandLabel = new JLabel(car.getBrand());

        licenseLabelQue.add(licenseLabel);
        brandLabelQue.add(brandLabel);
        scrollContent.add(licenseLabel, 0, 0);
        scrollContent.add(brandLabel, 0, 1);
    }

    @Override
    public void onCarExit(Car car) {
    }

    @Override
    public void onCarPayment(Car car, double revenue) {
    }
}
