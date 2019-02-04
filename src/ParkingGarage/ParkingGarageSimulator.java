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
    public static final int TAG_THROUGHPUT = 0;
    private static final double PRICE = 3.00;

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
        csg = new CarSpawnGenerator(200, 10, 5);
    }

    /**
     * Method to start running a thread for the whole operation.
     */
    public void start() {
        thread.start();
        paused = false;
    }

    /**
     * Method that toggles the pause by reversing it.
     */
    public void togglePause() {
        paused = !paused;
    }

    /**
     * Method that gives the boolean for if the program is paused.
     * @return paused boolean to check if the program is paused.
     */
    public boolean isPaused() {
        return paused;
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
                                    raiseCarExit(car);
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

                for (int i=0; entryQueue.carsInQueue() > 0 && i < entryQueue.getEntrySpeed() && parkingGarage.getFirstFreeLocation() != null; ++i) {
                    Car car = entryQueue.removeCar();
                    Location freeLocation = parkingGarage.getFirstFreeLocation(car);
                    parkingGarage.setCarAt(freeLocation, car);
                    raiseCarEnter(car);
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

    /**
     * Method to perform all the statistics related operations per tick.
     */
    private void performStatisticTick() {
        if (lastHour == calendar.get(Calendar.HOUR_OF_DAY)) {
            return;
        }

        statisticManager.updateDataSet(TAG_THROUGHPUT, parkingGarage.getCarCount());
        lastHour = calendar.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * Method to raise all the attached ParkingGarageSimulatorListeners onTick methods at the end of the tick.
     */
    private void performListenerTick() {
        raiseTick();
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
        performListenerTick();
    }

    /**
     * Method to call all the attached ParkingGarageSimulatorListeners onTick methods.
     */
    private void raiseTick() {
        for (ParkingGarageSimulatorListener listener : listeners)
            if (listener != null)
                listener.onTick();
    }

    /**
     * Method to call all the attached ParkingGarageSimulatorListeners onCarEnter methods.
     * @param car the car that has been added to the ParkingGarage.
     */
    private void raiseCarEnter(Car car) {
        for (ParkingGarageSimulatorListener listener : listeners)
            if (listener != null)
                listener.onCarEnter(car);

    }

    /**
     * Method to call all the attached ParkingGarageSimulatorListeners onCarExit methods.
     * @param car the car that has been added to the ParkingGarage.
     */
    private void raiseCarExit(Car car) {
        for (ParkingGarageSimulatorListener listener : listeners)
            if (listener != null)
                listener.onCarExit(car);
    }

    /**
     * Method to call all the attached ParkingGarageSimulatorListeners onCarPayment methods.
     * @param car the car that has paid.
     * @param revenue the amount the car has paid.
     */
    private void raiseCarPayment(Car car, double revenue) {
        for (ParkingGarageSimulatorListener listener : listeners)
            if (listener != null)
                listener.onCarPayment(car, revenue);
    }

    /**
     * Attaches a ParkingGarageSimulatorListener to the ParkingGarageSimulator.
     * @param listener the ParkingGarageSimulatorListener to attach.
     */
    public void addParkingGarageSimulatorListener(ParkingGarageSimulatorListener listener) {
        listeners.add(listener);
    }

    /**
     * Sets the new timescale of the ParkingGarageSimulator.
     * @param timescale the new timescale.
     */
    public void setTimescale(int timescale) {
        this.timescale = timescale;
    }

    /**
     * @return timescale the timescale of the ParkingGarageSimulator.
     */
    public int getTimescale() {
        return timescale;
    }

    /**
     * @return the current date of the simulation.
     */
    public Date getDate() {
        return calendar.getTime();
    }

    /**
     * @return timescale the timescale of the ParkingGarageSimulator.
     */
    public Calendar getCalendar() {
        return calendar;
    }

    /**
     * Method to call to start running the simulation.
     */
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

    /**
     * Updates the attached parkingGarageView.
     */
    public void updateViews() {
        interfaceContext.getSimulatorPanel().getParkingGarageView().updateView(parkingGarage);
        statisticManager.updateView();
    }

}
