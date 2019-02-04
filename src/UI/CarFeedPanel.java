package UI;

import Application.ApplicationState;
import Car.Car;
import ParkingGarage.ParkingGarageSimulatorListener;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.LinkedList;
import java.util.Queue;

public class CarFeedPanel extends JPanel implements ParkingGarageSimulatorListener {

    private static final int MAX_SIZE = 100;
    private static final String[] COLUMNS = {"License plate", "Brand", "Type", "Minutes"};

    private JTable scrollContent;
    private JScrollPane scrollPane;
    private Queue<String[]> labelQueue;
    private ApplicationState applicationState;

    /**
     * Constructor for the CarFeedPanel taking zero parameters with set values.
     */
    public CarFeedPanel() {
        applicationState = ApplicationState.getInstance();
        applicationState.getParkingGarageSimulator().addParkingGarageSimulatorListener(this);

        setMinimumSize(new Dimension(100, 64));
        setBorder(Theme.getDefaultBorder());
        setLayout (new BorderLayout());
        labelQueue = new LinkedList<>();

        scrollContent = new JTable(new DefaultTableModel(new String[][]{}, COLUMNS));
        scrollPane = new JScrollPane(scrollContent);
        add(scrollPane, BorderLayout.CENTER);

        setVisible(true);
    }

    @Override
    public void onTick() {
    }

    @Override
    public void onCarEnter(Car car) {
        DefaultTableModel model = ((DefaultTableModel)scrollContent.getModel());
        if (scrollContent.getRowCount() > MAX_SIZE) {
            model.removeRow(model.getRowCount() - 1);
        }
        model.insertRow(0, new String[]{car.getLicensePlate(), car.getBrand(), String.valueOf(car.getInitialMinutesLeft()), car.getTypeCarByColor()});
        revalidate();
    }

    @Override
    public void onCarExit(Car car) {
    }

    @Override
    public void onCarPayment(Car car, double revenue) {
    }
}
