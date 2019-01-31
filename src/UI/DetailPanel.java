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
    private JLabel adHocCarLabel;
    private JLabel parkingPassCarLabel;
    private JLabel reservationCarLabel;
    private JLabel electricCarLabel;
    private JLabel disabledCarLabel;
    private JLabel adHocCarLegendLabel;
    private JLabel disabledLegendLabel;
    private JLabel electricLegendLabel;
    private JLabel parkingPassLegendLabel;
    private JLabel reservationLegendLabel;



    private int dutch;
    private int germans;
    private int belgians;
    private int totalCars;
    private int daysPassed;
    private int dayOfWeek;
    private int adHocCar;
    private int parkingPassCar;
    private int reservationCar;
    private int electricCar;
    private int disabledCar;

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
        avgIncomeLabel = new JLabel();
        avgIncomeDayLabel = new JLabel();
        adHocCarLabel = new JLabel();
        parkingPassCarLabel = new JLabel();
        reservationCarLabel = new JLabel();
        electricCarLabel = new JLabel();
        disabledCarLabel = new JLabel();
        adHocCarLegendLabel = new JLabel("<html><b>Normal car</b></html>");
        disabledLegendLabel = new JLabel("<html><b>Disabled passenger car</b></html>");
        electricLegendLabel = new JLabel("<html><b>Electric car</b></html>");
        parkingPassLegendLabel = new JLabel("<html><b>Parking Pass car</b></html>");
        reservationLegendLabel = new JLabel("<html><b>Reservation car</b></html>");

        adHocCarLegendLabel.setForeground(MaterialColors.RED_500);
        disabledLegendLabel.setForeground(MaterialColors.BLUE_900);
        electricLegendLabel.setForeground(MaterialColors.GREEN_500);
        parkingPassLegendLabel.setForeground(MaterialColors.TEAL_300);
        reservationLegendLabel.setForeground(MaterialColors.YELLOW_A700);

        revenueLabel.setForeground(MaterialColors.GREEN_500);
        avgIncomeLabel.setForeground(MaterialColors.GREEN_500);
        avgIncomeDayLabel.setForeground(MaterialColors.GREEN_500);


        content.add(reservationLegendLabel, 0, 0);
        content.add(new JLabel(), 0, 0);
        content.add(parkingPassLegendLabel, 0, 0);
        content.add(new JLabel(), 0, 0);
        content.add(electricLegendLabel, 0, 0);
        content.add(new JLabel(), 0, 0);
        content.add(disabledLegendLabel, 0, 0);
        content.add(new JLabel(), 0, 0);
        content.add(adHocCarLegendLabel, 0, 0);
        content.add(new JLabel(), 0, 0);
        content.add(new JLabel("Legend:"), 0, 0);
        content.add(new JLabel(), 0, 0);
        content.add(new JLabel(), 0, 1);
        content.add(new JLabel("Belgians"), 0, 0);
        content.add(belgianLabel, 0, 1);
        content.add(new JLabel("Germans"), 0, 0);
        content.add(germanLabel, 0, 1);
        content.add(new JLabel("Dutch"), 0, 0);
        content.add(dutchLabel, 0, 1);
        content.add(new JLabel("Nationalities: "), 0, 0);
        content.add(new JLabel(), 0, 1);
        content.add(new JLabel(), 0, 0);
        content.add(new JLabel(), 0, 1);
        content.add(new JLabel("Disabled"), 0, 0);
        content.add(disabledCarLabel, 0, 1);
        content.add(new JLabel("Electric"), 0, 0);
        content.add(electricCarLabel, 0, 1);
        content.add(new JLabel("Reservation"), 0, 0);
        content.add(reservationCarLabel, 0, 1);
        content.add(new JLabel("Parking pass"), 0, 0);
        content.add(parkingPassCarLabel, 0, 1);
        content.add(new JLabel("Normal"), 0, 0);
        content.add(adHocCarLabel, 0, 1);
        content.add(new JLabel("Type cars: "), 0, 0);
        content.add(new JLabel(), 0, 1);
        content.add(new JLabel(), 0, 0);
        content.add(new JLabel(), 0, 1);
        content.add(new JLabel("Average revenue per day"), 0, 0);
        content.add(avgIncomeDayLabel, 0, 1);
        content.add(new JLabel("Average revenue per car"), 0, 0);
        content.add(avgIncomeLabel, 0, 1);
        content.add(new JLabel("Total revenue"), 0, 0);
        content.add(revenueLabel, 0, 1);
        content.add(new JLabel("Total cars seen"), 0, 0);
        content.add(carsLabel, 0, 1);
        content.add(new JLabel("Total days passed"), 0, 0);
        content.add(daysPassedLabel, 0, 1);
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

        if (car.getColor() == Color.red) {
            adHocCarLabel.setText(String.valueOf(++adHocCar));
        }

        if (car.getColor() == Color.blue) {
            disabledCarLabel.setText(String.valueOf(++disabledCar));
        }

        if (car.getColor() == Color.green) {
            electricCarLabel.setText(String.valueOf(++electricCar));
        }

        if (car.getColor() == Color.orange) {
            parkingPassCarLabel.setText(String.valueOf(++parkingPassCar));
        }

        if (car.getColor() == Color.yellow) {
            reservationCarLabel.setText(String.valueOf(++reservationCar));
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
