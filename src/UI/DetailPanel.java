package UI;

import Application.ApplicationState;
import Car.Car;
import ParkingGarage.ParkingGarageSimulatorListener;
import mdlaf.utils.MaterialColors;

import javax.swing.*;
import java.awt.*;
import java.util.Calendar;

public class DetailPanel extends JPanel implements ParkingGarageSimulatorListener {

    private ApplicationState applicationState;
    private InterfaceContext interfaceContext;
    private Calendar calendar;

    private JLabel capacityLabel;
    private JLabel revenueLabel;
    private JLabel carsLabel;
    private JLabel dutchLabel;
    private JLabel germanLabel;
    private JLabel belgianLabel;
    private JLabel daysPassedLabel;
    private JLabel avgIncomeLabel;
    private JLabel avgIncomeDayLabel;
    private JLabel totalWorkersLabel;
    private JLabel garageWorkerLabel;
    private JLabel securityGuardWorkerLabel;

    private int dutch;
    private int germans;
    private int belgians;
    private int totalCars;
    private int daysPassed;
    private int dayOfWeek;
    private int totalWorkers;
    private int garageWorkers;
    private int securityGuardWorkers;

    private double totalRevenue;

    public DetailPanel() {
        applicationState = ApplicationState.getInstance();
        interfaceContext = InterfaceContext.getInstance();
        applicationState.getParkingGarageSimulator().addParkingGarageSimulatorListener(this);
        calendar = applicationState.getParkingGarageSimulator().getCalendar();
        dayOfWeek = -1;
        daysPassed = -1;

        setMinimumSize(new Dimension(400, 400));
        setBorder(Theme.getDefaultBorder());
        setLayout(new BorderLayout());

        JPanel content = new JPanel(new GridLayout(0, 2));
        capacityLabel = new JLabel();
        revenueLabel = new JLabel();
        carsLabel = new JLabel();
        dutchLabel = new JLabel();
        germanLabel = new JLabel();
        belgianLabel = new JLabel();
        daysPassedLabel = new JLabel();
        totalWorkersLabel = new JLabel();
        garageWorkerLabel = new JLabel();
        securityGuardWorkerLabel = new JLabel();
        avgIncomeLabel = new JLabel();
        avgIncomeDayLabel = new JLabel();

        revenueLabel.setForeground(MaterialColors.GREEN_500);
        avgIncomeLabel.setForeground(MaterialColors.GREEN_500);
        avgIncomeDayLabel.setForeground(MaterialColors.GREEN_500);

        content.add(new JLabel("Average revenue per day"), 0, 0);
        content.add(avgIncomeDayLabel, 0, 1);
        content.add(new JLabel("Average revenue per car"), 0, 0);
        content.add(avgIncomeLabel, 0, 1);
        content.add(new JLabel(), 0, 0);
        content.add(new JLabel(), 0, 1);
        content.add(new JLabel("Total days passed"), 0, 0);
        content.add(daysPassedLabel, 0, 1);
        content.add(new JLabel("Total belgians"), 0, 0);
        content.add(belgianLabel, 0, 1);
        content.add(new JLabel("Total germans"), 0, 0);
        content.add(germanLabel, 0, 1);
        content.add(new JLabel("Total dutch"), 0, 0);
        content.add(dutchLabel, 0, 1);
        content.add(new JLabel("Total cars seen"), 0, 0);
        content.add(carsLabel, 0, 1);
        content.add(new JLabel("Total revenue"), 0, 0);
        content.add(revenueLabel, 0, 1);
        content.add(new JLabel("Current capacity"), 0, 0);
        content.add(capacityLabel, 0, 1);

        add(content, BorderLayout.PAGE_START);
    }

    @Override
    public void onTick() {
        capacityLabel.setText("" + applicationState.getParkingGarage().getCarCount() + "/" + applicationState.getParkingGarage().getLocationCount());
        if (calendar.get(Calendar.DAY_OF_WEEK) != dayOfWeek) {
            daysPassed++;
            if (totalRevenue > 0 && daysPassed > 0) {
                avgIncomeDayLabel.setText(String.format("€%1.2f", totalRevenue / daysPassed));
            }
            dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
            daysPassedLabel.setText(String.valueOf(daysPassed));
        }
    }

    @Override
    public void onCarEnter(Car car) {
        if (car.getLicensePlate().startsWith("NL")) {
            dutchLabel.setText(String.valueOf(++dutch));
        }

        if (car.getLicensePlate().startsWith("D")) {
            germanLabel.setText(String.valueOf(++germans));

        }

        if (car.getLicensePlate().startsWith("B")) {
            belgianLabel.setText(String.valueOf(++belgians));
        }

        totalCars = dutch + germans + belgians;
        carsLabel.setText(String.valueOf(totalCars));
    }

    @Override
    public void onCarPayment(Car car, double revenue) {
        this.totalRevenue += revenue;
        revenueLabel.setText(String.format("€%1.2f", totalRevenue));
        avgIncomeLabel.setText(String.format("€%1.2f", totalRevenue / totalCars));
    }

    @Override
    public void onCarExit(Car car) {
    }
}
