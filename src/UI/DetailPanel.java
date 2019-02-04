package UI;

import Application.ApplicationState;
import Car.Car;
import Generator.WorkerGenerator;
import ParkingGarage.ParkingGarageSimulatorListener;
import Workers.Worker;
import mdlaf.utils.MaterialColors;
import mdlaf.utils.MaterialImages;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Calendar;

public class DetailPanel extends JPanel implements ParkingGarageSimulatorListener {

    private ApplicationState applicationState;
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
    private JLabel dayOfWeekLabelProfit;
    private JLabel adHocCarLabel;
    private JLabel parkingPassCarLabel;
    private JLabel reservationCarLabel;
    private JLabel electricCarLabel;
    private JLabel disabledCarLabel;
    private JLabel hourlyWorkerCostLabel;
    private JLabel garageWorkerLabel;
    private JLabel securityGuardWorkerLabel;
    private JLabel dailyWorkerCostLabel;
    private JLabel avgDailyStaffCost;
    private JLabel dayOfWeekLabel;
    private JLabel avgProfitDayLabel;
    private WorkerGenerator wg;
    private ArrayList<Worker> workers;

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

    private int garageWorkers;
    private int securityGuardWorkers;
    private int totalWorkers;
    private double avgDailyCosts;
    private double costsDaily;



    private double totalRevenue;

    /**
     * Constructor for the DetailPanel object with zero parameters and set values.
     */
    public DetailPanel() {
        applicationState = ApplicationState.getInstance();
        applicationState.getParkingGarageSimulator().addParkingGarageSimulatorListener(this);
        calendar = applicationState.getParkingGarageSimulator().getCalendar();
        dayOfWeek = -1;
        daysPassed = -1;

        wg = new WorkerGenerator();
        workers = wg.generateWorkers(3, 7);
        garageWorkers = wg.getW();
        securityGuardWorkers = wg.getSg();
        totalWorkers = garageWorkers + securityGuardWorkers;

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
        avgProfitDayLabel = new JLabel();
        adHocCarLabel = new JLabel();
        parkingPassCarLabel = new JLabel();
        reservationCarLabel = new JLabel();
        electricCarLabel = new JLabel();
        disabledCarLabel = new JLabel();
        avgDailyStaffCost = new JLabel();
        dailyWorkerCostLabel = new JLabel();
        dayOfWeekLabel = new JLabel();
        dayOfWeekLabelProfit = new JLabel();

        JLabel adHocCarLegendLabel = new JLabel("<html><b>Normal car</b></html>");
        JLabel disabledLegendLabel = new JLabel("<html><b>Disabled passenger car</b></html>");
        JLabel electricLegendLabel = new JLabel("<html><b>Electric car</b></html>");
        JLabel parkingPassLegendLabel = new JLabel("<html><b>Parking Pass car</b></html>");
        JLabel reservationLegendLabel = new JLabel("<html><b>Reservation car</b></html>");

        adHocCarLegendLabel.setForeground(MaterialColors.RED_500);
        disabledLegendLabel.setForeground(MaterialColors.BLUE_900);
        electricLegendLabel.setForeground(MaterialColors.GREEN_500);
        parkingPassLegendLabel.setForeground(MaterialColors.TEAL_300);
        reservationLegendLabel.setForeground(MaterialColors.YELLOW_A700);

        avgProfitDayLabel.setForeground(MaterialColors.GREEN_500);
        dailyWorkerCostLabel.setForeground(MaterialColors.RED_500);
        hourlyWorkerCostLabel.setForeground(MaterialColors.RED_500);
        revenueLabel.setForeground(MaterialColors.GREEN_500);
        avgIncomeLabel.setForeground(MaterialColors.GREEN_500);
        avgIncomeDayLabel.setForeground(MaterialColors.GREEN_500);
        avgDailyStaffCost.setForeground(MaterialColors.RED_500);

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
        content.add(avgProfitDayLabel, 0, 0);
        content.add(dayOfWeekLabelProfit, 0, 0);
        content.add(new JLabel(), 0, 0);
        content.add(new JLabel(), 0, 1);
        content.add(dayOfWeekLabel, 0, 0);
        content.add(dailyWorkerCostLabel, 0, 1);
        content.add(new JLabel("Average daily staff cost"), 0, 0);
        content.add(avgDailyStaffCost, 0, 1);
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
        setWorkerAmount();
        capacityLabel.setText("" + applicationState.getParkingGarage().getCarCount() + "/" + applicationState.getParkingGarage().getLocationCount());
        if (calendar.get(Calendar.DAY_OF_WEEK) != dayOfWeek) {
            daysPassed++;
            if (totalRevenue > 0 && daysPassed > 0) {
                avgIncomeDayLabel.setText(String.format("€%1.2f", totalRevenue / daysPassed));
                avgProfitDayLabel.setText(String.format("€%1.2f", (totalRevenue / daysPassed) - costsDaily));
            } else {
                avgIncomeDayLabel.setText("Calculating...");
                avgProfitDayLabel.setText("Calculating...");
            }
            dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
            daysPassedLabel.setText(String.valueOf(daysPassed));

        }

        if (totalRevenue == 0.00) {
            avgIncomeLabel.setText("Calculating...");
            revenueLabel.setText("Calculating...");
        }

        if (adHocCar == 0) {
            adHocCarLabel.setText("0");
        }

        if (parkingPassCar == 0) {
            parkingPassCarLabel.setText("0");
        }

        if (reservationCar == 0) {
            reservationCarLabel.setText("0");
        }

        if (electricCar == 0) {
            electricCarLabel.setText("0");
        }

        if (disabledCar == 0) {
            disabledCarLabel.setText("0");
        }
    }

    @Override
    public void onCarEnter(Car car) {
        if (car.getLicensePlate().startsWith("NL")) {
            dutchLabel.setText(String.valueOf(++dutch));
        } else if (dutch == 0) {
            dutchLabel.setText("0");
        }

        if (car.getLicensePlate().startsWith("D")) {
            germanLabel.setText(String.valueOf(++germans));
        } else if (germans == 0) {
            germanLabel.setText("0");
        }

        if (car.getLicensePlate().startsWith("B")) {
            belgianLabel.setText(String.valueOf(++belgians));
        } else if (belgians == 0) {
            belgianLabel.setText("0");
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

    /**
     * Method to set the worker labels inside of the simulation with set values and weekend compensation.
     * Supposed to be updated each tick.
     */
    public void setWorkerAmount() {
        dayOfWeekLabel.setText(wg.getDayName(calendar.get(Calendar.DAY_OF_WEEK)) + " staff cost");
        dayOfWeekLabelProfit.setText(wg.getDayName(calendar.get(Calendar.DAY_OF_WEEK)) + " profit");
        double costsWorkers = 0;
        double costsSecurity = 0;
        double weekendDiff = 10.50;
        int weekendSg = (int) Math.ceil(securityGuardWorkers / 2) + 1;
        int weekendW = (int) Math.ceil(garageWorkers / 2) + 1;

        for (Worker i : workers) {
            if (i.getWorkDesc().toLowerCase().contains("worker")) {
                costsWorkers += i.getPerHour();
            }
            if (i.getWorkDesc().toLowerCase().contains("security")) {
                costsSecurity += i.getPerHour();
            }
        }
        avgDailyCosts = ((((24 * costsSecurity) + (11 * costsWorkers)) * 5) + ((24 * (costsSecurity / 2)) + ((11 * (costsWorkers / 2)))) + ((24 * (((costsSecurity / 2)) + weekendDiff) * 1.2)) + ((11 * (((costsWorkers / 2)) * 1.2)))) / 7;
        avgDailyStaffCost.setText(String.format("€%1.2f", avgDailyCosts));

        if (calendar.get(Calendar.DAY_OF_WEEK) > 1 && calendar.get(Calendar.DAY_OF_WEEK) < 7) {
            costsDaily = (24 * costsSecurity) + (11 * costsWorkers);
            dailyWorkerCostLabel.setText(String.format("€%1.2f", costsDaily));
            securityGuardWorkerLabel.setText(String.valueOf(securityGuardWorkers));
            if (calendar.get(Calendar.HOUR_OF_DAY) == 7) {
                garageWorkerLabel.setText(String.valueOf(garageWorkers));
            }

            if (calendar.get(Calendar.HOUR_OF_DAY) > 17 || (calendar.get(Calendar.HOUR_OF_DAY) >= 0 && calendar.get(Calendar.HOUR_OF_DAY) <= 6)) {
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
