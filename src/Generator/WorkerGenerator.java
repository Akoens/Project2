package Generator;

import Workers.Worker;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

public class WorkerGenerator {

    private Random rd;
    private double spawnRate;
    private ArrayList workers;

    public WorkerGenerator(int sg, int w) {
        rd = new Random();
        workers = new ArrayList<>();
        for (int x = 0; x <= sg; x++) {
            workers.add(randomWorker("Security Guard: " + x));
        }
        for (int x = 0; x <= w; x++) {
            workers.add(randomWorker("Worker: " + x));
        }
    }

    public Worker randomWorker(String job) {
        int decideAge = rd.nextInt(78) + 18;
        return new Worker(job, decideAge, randomStayMinutes(550));

    }

    public int randomStayMinutes(int minimumMinutes) {
        if (rd.nextInt(100) < 50) {
            return minimumMinutes - rd.nextInt(minimumMinutes / 30);
        } else {
            return minimumMinutes + rd.nextInt(minimumMinutes / 30);
        }
    }

    public ArrayList<Worker> workerGeneration(Calendar calendar) {
        ArrayList<>
                spawnRate = rd.nextDouble();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int dayNumber = calendar.get(Calendar.DAY_OF_WEEK);
        boolean isWeekend;

        if (dayNumber == 1 || dayNumber == 7) {
            isWeekend = true;
        } else {
            isWeekend = false;
        }

        if (hour == 7 && !isWeekend) {

        }

    }
}

