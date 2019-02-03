package Generator;

import Workers.Worker;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

public class WorkerGenerator {

    private Random rd;
    private ArrayList workers;
    private int sg;
    private int w;

    /**
     * Constructor that creates a new random and a new ArrayList of workers.
     */
    public WorkerGenerator() {
        rd = new Random();
        workers = new ArrayList<>();

    }

    /**
     * Method that generates new workers.
     * @param sg The number of security guards in the parking garage.
     * @param w The number of workers in the parking garage,
     * @return The ArrayList of workers.
     */
    public ArrayList<Worker> generateWorkers(int sg, int w) {
        this.sg = sg;
        this.w = w;
        int decideAge = rd.nextInt(51) + 18;
        for (int x = 0; x <= sg; x++) {
            workers.add(new Worker("Security Guard: " + x, decideAge, 10)); //basically infinite
        }
        for (int x = 0; x <= w; x++) {
            workers.add(new Worker("Worker " + x, decideAge, randomStayMinutes(550)));
        }
        return workers;
    }

    /**
     * @param minimumMinutes minimum time a worker stays in the parking garage.
     * @return the random amount of minutes that a worker stays.
     */
    public int randomStayMinutes(int minimumMinutes) {
        if (rd.nextInt(100) < 50) {
            return minimumMinutes - rd.nextInt(minimumMinutes / 30);
        } else {
            return minimumMinutes + rd.nextInt(minimumMinutes / 30);
        }
    }

    /**
     * @param x the number that indicates the day.
     * @return A String containing the day name.
     */
    public String getDayName(int x) {
        String result = "";
        switch (x) {
            case (1):
                result = "Sunday";
                break;
            case (2):
                result = "Monday";
                break;
            case 3:
                result = "Tuesday";
                break;
            case 4:
                result = "Wednesday";
                break;
            case 5:
                result = "Thursday";
                break;
            case 6:
                result = "Friday";
                break;
            case 7:
                result = "Saturday";
                break;
        }
        return result;
    }

    /**
     * @return The nmber of security guards in the garage.
     */
    public int getSg() {
        return sg;
    }

    /**
     * @return The number of regular workers in the garage.
     */
    public int getW() {
        return w;
    }
}

