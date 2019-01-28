package ParkingGarage;

import Car.*;
import Statistic.DataSet;
import Statistic.GraphView;
import Statistic.StatisticManager;
import Statistic.StatisticWindow;

import javax.swing.*;
import java.awt.*;
import java.util.Calendar;

public class ParkingGarageSimulator {

    public static final int TAG_THROUGHPUT = 0;
    public static final int TAG_TROUGHPUT = 1;

    private ParkingGarage parkingGarage;
    private ParkingGarageView parkingGarageView;

    private Thread thread;
    private Calendar calendar;

    private int lastHour;
    private StatisticManager statisticManager;

    //private double timeScale = 1d;  //Every real life second a simulated second passes
    //private double timeScale = 60d; //Every real life second a simulated minute passes
    private double timeScale = 3750d; //Every real life second a simulated hour passes

    public ParkingGarageSimulator(JFrame frame, ParkingGarage parkingGarage, ParkingGarageView parkingGarageView) {
        this.parkingGarage = parkingGarage;
        this.parkingGarageView = parkingGarageView;

        Point location = frame.getLocation();
        location.x += frame.getWidth();

        calendar = Calendar.getInstance();
        lastHour = calendar.get(Calendar.HOUR_OF_DAY);
        statisticManager = new StatisticManager(new StatisticWindow("Car flow", location, new GraphView("Number of cars", "Hour of day", Color.BLACK)));
        statisticManager.putDataSet(TAG_THROUGHPUT, new DataSet(new double[240], Color.BLUE));
        statisticManager.putDataSet(TAG_TROUGHPUT, new DataSet(new double[240], Color.GREEN));

        thread = new Thread(this::run);
    }

    public void start() {
        thread.start();
    }

    private void advanceTime() {
        calendar.add(Calendar.MINUTE, 1);
    }

    private void performCarTick() {
        for (Car[][] carFloor : parkingGarage.getCars())
            for (Car[] carRow : carFloor)
                for (Car car : carRow)
                    if (car != null) {
                        car.tick();
                    }
    }

    private void performCarExit() {
        for (CarQueue queue : parkingGarage.getCarQueues()) {
            if (queue instanceof CarExitQueue) {
                CarExitQueue exitQueue = (CarExitQueue) queue;

                for (Car[][] carFloor : parkingGarage.getCars())
                    for (Car[] carRow : carFloor)
                        for (Car car : carRow)
                            if (car != null && car.getMinutesLeft() <= 0 && !car.getIsPaying()) {
                                exitQueue.addCar(car);
                                parkingGarage.carLeavesSpot(car);
                            }


                for (int i = 0; exitQueue.carsInQueue() > 0 && i < exitQueue.getExitSpeed(); ++i) {
                    exitQueue.removeCar();
                }
            }
        }
    }

    private void performCarEntry() {
        for (CarQueue queue : parkingGarage.getCarQueues()) {
            if (queue instanceof CarEntryQueue) {
                CarEntryQueue entryQueue = (CarEntryQueue) queue;
                for (int i = 0; entryQueue.carsInQueue() > 0 && i < entryQueue.getEntrySpeed(); ++i) {
                    Car car = entryQueue.removeCar();
                    Location freeLocation = parkingGarage.getFirstFreeLocation();
                    parkingGarage.setCarAt(freeLocation, car);
                }
            }
        }
    }

    private void performStatisticTick() {
        if (lastHour == calendar.get(Calendar.HOUR_OF_DAY)) {
            return;
        }

        statisticManager.updateDataSet(TAG_THROUGHPUT, parkingGarage.getCarCount());

        lastHour = calendar.get(Calendar.HOUR_OF_DAY);
    }

    private void tick() {
        performCarTick();
        performCarExit();
        performCarEntry();
        performStatisticTick();
    }

    private void run() {
        while (true) {
            try {
                advanceTime();
                tick();
                updateView();
                Thread.sleep((long) (60000d / timeScale));
            } catch (InterruptedException e) {
                return;
            }
        }
    }

    public void updateView() {
        parkingGarageView.updateView(calendar.getTime(), parkingGarage);
        parkingGarageView.repaint();
        parkingGarageView.revalidate();
    }

}
