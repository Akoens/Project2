package ParkingGarage;

import Car.*;
import Statistic.DataSet;
import Statistic.GraphView;
import Statistic.StatisticWindow;

import java.awt.*;
import java.util.ArrayList;
import java.util.Calendar;

public class ParkingGarageSimulator {

    private ParkingGarage parkingGarage;
    private ParkingGarageView parkingGarageView;

    private Thread thread;
    private Calendar calendar;

    private StatisticWindow statisticWindow;
    private ArrayList<DataSet> dataSetList;
    private double[] data;
    private int dataPos;
    private int lastHour;

    //private double timeScale = 1d;  //Every real life second a simulated second passes
    //private double timeScale = 60d; //Every real life second a simulated minute passes
    private double timeScale = 3750d; //Every real life second a simulated hour passes

    public ParkingGarageSimulator(ParkingGarage parkingGarage, ParkingGarageView parkingGarageView) {
        this.parkingGarage = parkingGarage;
        this.parkingGarageView = parkingGarageView;

        calendar = Calendar.getInstance();
        lastHour = calendar.get(Calendar.HOUR_OF_DAY);
        thread = new Thread(this::run);
        statisticWindow = new StatisticWindow("Car flow", new GraphView("Number of cars", "Hour of day", Color.BLACK));
        dataSetList = new ArrayList<>();
        data = new double[24];
        dataPos = 0;
    }

    public void start() {
        thread.start();
    }

    private void advanceTime() {
        calendar.add(Calendar.MINUTE, 1);
    }

    private void preformCarTick() {
        for (Car[][] carFloor : parkingGarage.getCars())
            for (Car[] carRow : carFloor)
                for (Car car : carRow)
                    if (car != null) {
                        car.tick();
                    }
    }

    private void preformCarExit() {
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

    private void preformCarEntry() {
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

        int count = parkingGarage.getCarCount();
        if (count > 0) {
            data[dataPos] = count;
            dataPos++;
        }

        if (dataPos > data.length - 1) {
            dataPos = 0;
        }

        dataSetList.clear();
        dataSetList.add(new DataSet(data, Color.BLUE));

        if (statisticWindow.getStatisticView() instanceof GraphView) {
            GraphView graphView = (GraphView) statisticWindow.getStatisticView();
            graphView.updateView(dataSetList);
        }
        lastHour = calendar.get(Calendar.HOUR_OF_DAY);
    }

    private void tick() {
        preformCarTick();
        preformCarExit();
        preformCarEntry();
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
