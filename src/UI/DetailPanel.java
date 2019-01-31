package UI;

import Application.ApplicationState;
import Car.Car;
import Generator.WorkerGenerator;
import ParkingGarage.ParkingGarageSimulatorListener;
import Workers.Worker;
import javafx.scene.paint.Material;
import mdlaf.utils.MaterialColors;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

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
    private JLabel hourlyWorkerCostLabel;
    private JLabel garageWorkerLabel;
    private JLabel securityGuardWorkerLabel;
    private JLabel dailyWorkerCostLabel;
    private JLabel dayOfWeekLabel;

    private WorkerGenerator wg;
    private ArrayList<Worker> workers;
    private Random rd;

    private int dutch;
    private int germans;
    private int belgians;
    private int totalCars;
    private int daysPassed;
    private int dayOfWeek;
    private int hourlyWorkerCosts;
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

        wg = new WorkerGenerator();
        workers = wg.generateWorkers(3, 7);
        hourlyWorkerCosts = workers.size();
        garageWorkers = wg.getW();
        securityGuardWorkers = wg.getSg();


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
        hourlyWorkerCostLabel = new JLabel();
        garageWorkerLabel = new JLabel();
        securityGuardWorkerLabel = new JLabel();
        avgIncomeLabel = new JLabel();
        avgIncomeDayLabel = new JLabel();
        dailyWorkerCostLabel = new JLabel();
        dayOfWeekLabel = new JLabel();

        dailyWorkerCostLabel.setForeground(MaterialColors.RED_500);
        hourlyWorkerCostLabel.setForeground(MaterialColors.RED_500);
        revenueLabel.setForeground(MaterialColors.GREEN_500);
        avgIncomeLabel.setForeground(MaterialColors.GREEN_500);
        avgIncomeDayLabel.setForeground(MaterialColors.GREEN_500);


        content.add(dayOfWeekLabel, 0, 0);
        content.add(dailyWorkerCostLabel, 0, 1);
        content.add(new JLabel("Current hourly staff cost"), 0, 0);
        content.add(hourlyWorkerCostLabel, 0, 1);
        content.add(new JLabel("Current security guards"), 0, 0);
        content.add(securityGuardWorkerLabel, 0, 1);
        content.add(new JLabel("Current garage workers"), 0, 0);
        content.add(garageWorkerLabel, 0, 1);
        content.add(new JLabel(), 0, 0);
        content.add(new JLabel(), 0, 1);
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
        setWorkerAmount();
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

    public void setWorkerAmount() {
        dayOfWeekLabel.setText(wg.getDayName(calendar.get(Calendar.DAY_OF_WEEK)) + " 24h staff cost");
        double costsWorkers = 0;
        double costsSecurity = 0;
        double costsDaily;
        double weekendDiff = 10.50;
        int weekendSg = (int) Math.ceil(securityGuardWorkers / 2) + 1;
        int weekendW = (int) Math.ceil(garageWorkers / 2) + 1;

        for (Worker i : workers) {
            costsWorkers += i.getPerHour();
            if (i.getWorkDesc().toLowerCase().contains("security")) {
                costsSecurity += i.getPerHour();
            }
        }

        if (calendar.get(Calendar.DAY_OF_WEEK) > 1 && calendar.get(Calendar.DAY_OF_WEEK) < 7) {
            costsDaily = (24 * costsSecurity) + (11 * costsWorkers);
            dailyWorkerCostLabel.setText(String.format("€%1.2f", costsDaily));
            securityGuardWorkerLabel.setText("" + securityGuardWorkers);
            if (calendar.get(Calendar.HOUR_OF_DAY) == 7) {
                garageWorkerLabel.setText("" + garageWorkers);
            }

            if (calendar.get(Calendar.HOUR_OF_DAY) == 18) {
                garageWorkerLabel.setText("0");
            }

            if (calendar.get(Calendar.HOUR_OF_DAY) >= 7 && calendar.get(Calendar.HOUR_OF_DAY) <= 18) {
                hourlyWorkerCostLabel.setText(String.format("€%1.2f", costsWorkers + costsSecurity));
            } else {
                hourlyWorkerCostLabel.setText(String.format("€%1.2f", costsSecurity));
            }
        } else if (calendar.get(Calendar.DAY_OF_WEEK) == 7) {
            costsDaily = (24 * (costsSecurity / 2)) + (11 * (costsWorkers / 2));
            dailyWorkerCostLabel.setText(String.format("€%1.2f", costsDaily));
            securityGuardWorkerLabel.setText("" + weekendSg);
            if (calendar.get(Calendar.HOUR_OF_DAY) == 7) {
                garageWorkerLabel.setText("" + weekendW);
            }
            if (calendar.get(Calendar.HOUR_OF_DAY) == 18) {
                garageWorkerLabel.setText("0");
            }
            if (calendar.get(Calendar.HOUR_OF_DAY) >= 7 && calendar.get(Calendar.HOUR_OF_DAY) <= 18) {
                hourlyWorkerCostLabel.setText(String.format("€%1.2f", (costsWorkers + costsSecurity / 2)));
            } else {
                hourlyWorkerCostLabel.setText(String.format("€%1.2f", (costsSecurity / 2)));
            }
        } else if (calendar.get(Calendar.DAY_OF_WEEK) == 1) {
            costsDaily = (24 * ((costsSecurity / 2) + weekendDiff) * 1.2) + (11 * ((costsWorkers / 2)) * 1.2);
            dailyWorkerCostLabel.setText(String.format("€%1.2f", costsDaily));
            securityGuardWorkerLabel.setText("" + weekendSg);
            if (calendar.get(Calendar.HOUR_OF_DAY) == 7) {
                garageWorkerLabel.setText("" + weekendW);
            }
            if (calendar.get(Calendar.HOUR_OF_DAY) == 18) {
                garageWorkerLabel.setText("0");
            }
            if (calendar.get(Calendar.HOUR_OF_DAY) >= 7 && calendar.get(Calendar.HOUR_OF_DAY) <= 18) {
                hourlyWorkerCostLabel.setText(String.format("€%1.2f", (costsWorkers + costsSecurity / 2) * 1.5));
            } else {
                hourlyWorkerCostLabel.setText(String.format("€%1.2f", (costsSecurity / 2) * 1.5));
            }
        }
    }
}
