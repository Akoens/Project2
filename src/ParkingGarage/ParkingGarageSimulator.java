package ParkingGarage;

import Car.*;
import Generator.CarSpawnGenerator;
import Statistic.DataSet;
import Statistic.GraphView;
import Statistic.StatisticManager;
import Statistic.StatisticWindow;

import javax.swing.*;
import java.awt.*;
import java.util.Calendar;

public class ParkingGarageSimulator {

    private CarSpawnGenerator csg;

    public static final int TAG_THROUGHPUT = 0;

    private ParkingGarage parkingGarage;
    private ParkingGarageView parkingGarageView;

    private Thread thread;
    private Calendar calendar;

    private double amountPaid = 0;
    private static final double PRICE = 2.00;

    private int lastHour;
    private StatisticManager statisticManager;

    //private double timeScale = 1d;  //Every real life second a simulated second passes
    //private double timeScale = 60d; //Every real life second a simulated minute passes
    private double timeScale = 3750d; //Every real life second a simulated hour passes

    /**
     * Constructor for the ParkingGarageSimulator itself with three parameters.
     *
     * @param frame             a Jframe object.
     * @param parkingGarage     a ParkingGarage object.
     * @param parkingGarageView a ParkingGarageView object.
     */
    public ParkingGarageSimulator(JFrame frame, ParkingGarage parkingGarage, ParkingGarageView parkingGarageView) {
        this.parkingGarage = parkingGarage;
        this.parkingGarageView = parkingGarageView;

        Point location = frame.getLocation();
        location.x += frame.getWidth();

        calendar = Calendar.getInstance();
        lastHour = calendar.get(Calendar.HOUR_OF_DAY);
        statisticManager = new StatisticManager(new StatisticWindow("Car flow", location, new GraphView("Number of cars", "Hour of day", Color.BLACK)));
        statisticManager.putDataSet(TAG_THROUGHPUT, new DataSet(new double[240], Color.BLUE));

        thread = new Thread(this::run);
        csg = new CarSpawnGenerator(200, 10, 5);
    }

    /**
     * Method to start running a thread for the whole operation.
     */
    public void start() {
        thread.start();
    }

    /**
     * Method to increase the time by one minute
     */
    private void advanceTime() {
        calendar.add(Calendar.MINUTE, 1);
    }

    /**
     * Method to perform a car tick where the car gets moved to its location.
     */
    private void performCarTick() {
        for (Location[][] locationFloor : parkingGarage.getLocations())
            for (Location[] LocationRow : locationFloor)
                for (Location location : LocationRow)
                    if (location != null && location.hasCar()) {
                        location.getCar().tick();
                    }
    }

    /**
     * Method to perform a car exit tick where the car gets removed from the simulation.
     */
    private void performCarExit() {
        for (CarQueue queue : parkingGarage.getCarQueues()) {
            if (queue instanceof CarExitQueue) {
                CarExitQueue exitQueue = (CarExitQueue) queue;

                for (Location[][] locationFloor : parkingGarage.getLocations())
                    for (Location[] LocationRow : locationFloor)
                        for (Location location : LocationRow)
                            if (location != null && location.hasCar()) {
                                Car car = location.getCar();
                                if (car != null && car.getMinutesLeft() <= 0 && !car.getIsPaying() && !car.getHasToPay()) {
                                    exitQueue.addCar(car);
                                    parkingGarage.carLeavesSpot(car);
                                }
                            }

                for (int i = 0; exitQueue.carsInQueue() > 0 && i < exitQueue.getExitSpeed(); ++i) {
                    exitQueue.removeCar();
                }
            }
        }
    }

    /**
     * Method to perform entry of a car into the parking garage.
     */
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

    /**
     * Method to perform the car generation, in order to allow cars to exist and spawn into the entry queue.
     */
    private void performCarGeneration() {
        for (CarQueue queue : parkingGarage.getCarQueues())
            if (queue instanceof CarEntryQueue)
                for (Car car : csg.carGeneration(calendar))
                    queue.addCar(car);
    }

    /**
     * Method to perform the car payment system which checks the cars inside of the queue if they need to pay.
     */
    public void performCarPayment() {
        for (CarQueue queue : parkingGarage.getCarQueues()) {
            if (queue instanceof CarPaymentQueue) {
                CarPaymentQueue paymentQueue = (CarPaymentQueue) queue;

                for (Location[][] locationFloor : parkingGarage.getLocations())
                    for (Location[] LocationRow : locationFloor)
                        for (Location location : LocationRow)
                            if (location != null && location.hasCar()) {
                                Car car = location.getCar();
                                if (car != null && car.getMinutesLeft() <= 0 && !car.getIsPaying() && car.getHasToPay()) {
                                    paymentQueue.addCar(car);
                                    car.setIsPaying(true);
                                }
                            }

                int i = 0;
                while (paymentQueue.carsInQueue() > 0 && i < paymentQueue.getPaymentSpeed()) {
                    Car car = paymentQueue.removeCar();
                    if (car instanceof AdHocCar) {
                        amountPaid += (double) car.getInitialMinutesLeft() / 60 * PRICE;
                    }
                    if (car instanceof ReservationCar) {
                        amountPaid += (double) car.getInitialMinutesLeft() / 60 * PRICE * 2;
                    }
                    i++;
                    car.setHasToPay(false);
                    car.setIsPaying(false);
                }
            }
        }
    }

    /**
     * Method to perform a simulation wide tick, which is a combination of all ticks.
     * This allows the simulation to function.
     */
    private void tick() {
        performCarTick();
        performCarGeneration();
        performCarPayment();
        performCarExit();
        performCarEntry();
        performStatisticTick();
    }

    /**
     * Method to perform a statistic tick, which allows the graph to be drawn according to the amount of cars in the garage.
     */
    private void performStatisticTick() {
        if (lastHour == calendar.get(Calendar.HOUR_OF_DAY)) {
            return;
        }

        statisticManager.updateDataSet(TAG_THROUGHPUT, parkingGarage.getCarCount());
        lastHour = calendar.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * Method to call to start running the simulation itself.
     */
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

    /**
     * Method which redraws the garage once a tick has passed inside the simulation.
     */
    public void updateView() {
        parkingGarageView.updateView(calendar.getTime(), parkingGarage, amountPaid);
        parkingGarageView.repaint();
        parkingGarageView.revalidate();
    }

}
