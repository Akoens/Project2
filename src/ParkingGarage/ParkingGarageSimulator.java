package ParkingGarage;

import Car.*;

import java.util.Calendar;

public class ParkingGarageSimulator {


    private ParkingGarage parkingGarage;
    private ParkingGarageView parkingGarageView;

    private Thread thread;
    private Calendar calendar;

    //private double timeScale = 1d;  //Every real life second a simulated second passes
    //private double timeScale = 60d; //Every real life second a simulated minute passes
    private double timeScale = 3750d; //Every real life second a simulated hour passes

    public ParkingGarageSimulator(ParkingGarage parkingGarage, ParkingGarageView parkingGarageView) {
        this.parkingGarage = parkingGarage;
        this.parkingGarageView = parkingGarageView;

        calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, 0);
        calendar.set(Calendar.DAY_OF_MONTH, 1);

        thread = new Thread(this::run);
    }

    public void start() {
        thread.start();
    }

    private void advanceTime() {
        calendar.add(Calendar.MINUTE, 1);
    }

    private void preformCarStep() {
        for (Car[][] carFloor : parkingGarage.getCars())
            for (Car[] carRow : carFloor)
                for (Car car : carRow)
                    if (car != null) {
                        car.step();
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


                for (int i=0; exitQueue.carsInQueue() > 0 && i < exitQueue.getExitSpeed(); ++i) {
                    exitQueue.removeCar();
                }
            }
        }
    }

    private void preformCarEntry() {
        for (CarQueue queue : parkingGarage.getCarQueues()) {
            if (queue instanceof CarEntryQueue) {
                CarEntryQueue entryQueue = (CarEntryQueue) queue;
                for (int i=0; entryQueue.carsInQueue() > 0 && i < entryQueue.getEntrySpeed(); ++i) {
                    Car car = entryQueue.removeCar();
                    Location freeLocation = parkingGarage.getFirstFreeLocation();
                    parkingGarage.setCarAt(freeLocation, car);
                }
            }
        }
    }

    private void step() {
        preformCarStep();
        preformCarExit();
        preformCarEntry();
    }

    private void run() {
        while (true) {
            try {
                advanceTime();
                step();
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
