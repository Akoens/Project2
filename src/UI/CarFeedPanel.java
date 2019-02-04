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
    private Queue<JLabel> minutesLeftLabelQue;
    private Queue<JLabel> licenseLabelQue;
    private Queue<JLabel> typeCarLabelQue;
    private ApplicationState applicationState;

    /**
     * Constructor for the CarFeedPanel.
     */
    public CarFeedPanel() {
        applicationState = ApplicationState.getInstance();
        applicationState.getParkingGarageSimulator().addParkingGarageSimulatorListener(this);

        setMinimumSize(new Dimension(100, 64));
        setBorder(Theme.getDefaultBorder());
        setLayout (new BorderLayout());
        licenseLabelQue = new LinkedList<>();
        brandLabelQue = new LinkedList<>();
        minutesLeftLabelQue = new LinkedList<>();
        typeCarLabelQue = new LinkedList<>();

        scrollContent = new JPanel(new GridLayout(0, 4));
        scrollPane = new JScrollPane(scrollContent);
        add(new JLabel("License plate                      Brand                                   Type                              Minutes"), BorderLayout.PAGE_START);
        add(scrollPane, BorderLayout.CENTER);

        setVisible(true);
    }

    @Override
    public void onTick() {
    }

    @Override
    public void onCarEnter(Car car) {
        if (scrollContent.getComponentCount() > MAX_SIZE && licenseLabelQue.peek() != null && brandLabelQue.peek() != null && minutesLeftLabelQue.peek() != null && typeCarLabelQue.peek() != null) {
            scrollContent.remove(brandLabelQue.poll());
            scrollContent.remove(licenseLabelQue.poll());
            scrollContent.remove(minutesLeftLabelQue.poll());
            scrollContent.remove(typeCarLabelQue.poll());
        }
        JLabel licenseLabel = new JLabel(car.getLicensePlate());
        JLabel brandLabel = new JLabel(car.getBrand());
        JLabel minutesLeftLabel = new JLabel(String.valueOf(car.getInitialMinutesLeft()));
        JLabel typeCarLabel = new JLabel(car.getTypeCarByColor());


        licenseLabelQue.add(licenseLabel);
        brandLabelQue.add(brandLabel);
        typeCarLabelQue.add(typeCarLabel);
        minutesLeftLabelQue.add(minutesLeftLabel);
        scrollContent.add(licenseLabel, 0, 0);
        scrollContent.add(brandLabel, 0, 1);
        scrollContent.add(typeCarLabel, 0, 2);
        scrollContent.add(minutesLeftLabel, 0, 3);
    }

    @Override
    public void onCarExit(Car car) {
    }

    @Override
    public void onCarPayment(Car car, double revenue) {
    }
}
