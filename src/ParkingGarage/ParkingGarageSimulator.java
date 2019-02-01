package ParkingGarage;

import Car.*;
import Generator.CarSpawnGenerator;
import Statistic.DataSet;
import Statistic.StatisticManager;
import UI.InterfaceContext;

import java.awt.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class ParkingGarageSimulator {

    public static final int TIMESCALE_MIN = 1;
    public static final int TIMESCALE_MAX = 1000;
    private static final double PRICE = 3.00;


    public static final int TAG_THROUGHPUT = 0;

    private Thread thread;
    private Calendar calendar;
    private CarSpawnGenerator csg;
    private ParkingGarage parkingGarage;
    private InterfaceContext interfaceContext;
    private StatisticManager statisticManager;
    private ArrayList<ParkingGarageSimulatorListener> listeners;

    private boolean paused;
    private double revenue = 0;
    private int lastHour;
    private int timescale = 100;

    public ParkingGarageSimulator(ParkingGarage parkingGarage) {
        this.parkingGarage = parkingGarage;
        interfaceContext = InterfaceContext.getInstance();

        calendar = Calendar.getInstance();
        lastHour = calendar.get(Calendar.HOUR_OF_DAY);
        statisticManager = new StatisticManager();
        statisticManager.putDataSet(TAG_THROUGHPUT, new DataSet(new double[48], Color.BLUE));
        listeners = new ArrayList<ParkingGarageSimulatorListener>();

        thread = new Thread(this::run);
        csg = new CarSpawnGenerator();
    }

    public void start() {
        thread.start();
        paused = false;
    }

    public void togglePause() {
        paused = !paused;
    }

    public boolean isPaused() {
        return paused;
    }

    private void advanceTime() {
        calendar.add(Calendar.MINUTE, 1);
    }

    private void performCarTick() {
        for (Location[][] locationFloor : parkingGarage.getLocations())
            for (Location[] LocationRow : locationFloor)
                for (Location location : LocationRow)
                    if (location != null && location.hasCar()) {
                        location.getCar().tick();
                    }
    }

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
                                    raiseCarExit(car);
                                }
                            }

                for (int i=0; exitQueue.carsInQueue() > 0 && i < exitQueue.getExitSpeed(); ++i) {
                    exitQueue.removeCar();
                }
            }
        }
    }

    private void performCarEntry() {
        for (CarQueue queue : parkingGarage.getCarQueues()) {
            if (queue instanceof CarEntryQueue) {
                CarEntryQueue entryQueue = (CarEntryQueue) queue;
                for (int i=0; entryQueue.carsInQueue() > 0 && i < entryQueue.getEntrySpeed() && parkingGarage.getFirstFreeLocation() != null; ++i) {
                    Car car = entryQueue.removeCar();
                    Location freeLocation = parkingGarage.getFirstFreeLocation(car);
                    parkingGarage.setCarAt(freeLocation, car);
                    raiseCarEnter(car);
                }
            }
        }
    }

    private void performCarGeneration() {
        for (CarQueue queue : parkingGarage.getCarQueues())
            if (queue instanceof CarEntryQueue)
                for (Car car : csg.carGeneration(calendar))
                    queue.addCar(car);
    }

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
                    double amount = 0;
                    if (car instanceof AdHocCar || car instanceof ElectricCar || car instanceof DisabledCar) {
                        amount += (double) car.getInitialMinutesLeft() / 60 * PRICE;
                    }

                    if (car instanceof ReservationCar) {
                        amount += (double) car.getInitialMinutesLeft() / 60 * PRICE * 2;
                    }
                    i++;
                    car.setHasToPay(false);
                    car.setIsPaying(false);

                    revenue += amount;
                    raiseCarPayment(car, amount);
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

    private void performListenerTick() {
        raiseTick();
    }

    private void tick() {
        performCarTick();
        performCarGeneration();
        performCarPayment();
        performCarExit();
        performCarEntry();
        performStatisticTick();
        performListenerTick();
    }

    private void raiseTick() {
        for (ParkingGarageSimulatorListener listener : listeners)
            if (listener != null)
                listener.onTick();
    }

    private void raiseCarEnter(Car car) {
        for (ParkingGarageSimulatorListener listener : listeners)
            if (listener != null)
                listener.onCarEnter(car);

    }

    private void raiseCarExit(Car car) {
        for (ParkingGarageSimulatorListener listener : listeners)
            if (listener != null)
                listener.onCarExit(car);
    }

    private void raiseCarPayment(Car car, double revenue) {
        for (ParkingGarageSimulatorListener listener : listeners)
            if (listener != null)
                listener.onCarPayment(car, revenue);
    }

    public void addParkingGarageSimulatorListener(ParkingGarageSimulatorListener listener) {
        listeners.add(listener);
    }

    public void setTimescale(int timescale) {
        this.timescale = timescale;
    }

    public int getTimescale() {
        return timescale;
    }

    public Date getDate() {
        return calendar.getTime();
    }

    public double getRevenue() {
        return revenue;
    }

    public Calendar getCalendar() {
        return calendar;
    }

    private void run() {
        while (true) {
            try {
                if (!paused) {
                    advanceTime();
                    tick();
                }
                updateViews();
                interfaceContext.repaint();
                Thread.sleep(1000 / timescale);
            } catch (InterruptedException e) {
                return;
            }
        }
    }

    public void updateViews() {
        interfaceContext.getSimulatorPanel().getParkingGarageView().updateView(parkingGarage);
        statisticManager.updateView();
    }

}
