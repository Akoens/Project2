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

    public WorkerGenerator() {
        rd = new Random();
        workers = new ArrayList<>();

    }

    public ArrayList<Worker> generateWorkers(int sg, int w) {
        this.sg = sg;
        this.w = w;
        int decideAge = rd.nextInt(78) + 18;
        for (int x = 0; x <= sg; x++) {
            workers.add(new Worker("Security Guard: " + x, decideAge, 10000000));
        }
        for (int x = 0; x <= w; x++) {
            workers.add(new Worker("Worker " + x, decideAge, randomStayMinutes(550)));
        }
        return workers;
    }


    public int randomStayMinutes(int minimumMinutes) {
        if (rd.nextInt(100) < 50) {
            return minimumMinutes - rd.nextInt(minimumMinutes / 30);
        } else {
            return minimumMinutes + rd.nextInt(minimumMinutes / 30);
        }
    }

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

    public int getSg() {
        return sg;
    }

    public int getW() {
        return w;
    }
}

