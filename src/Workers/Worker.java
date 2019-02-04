package Workers;

public class Worker {
    private double perHour;
    private String workDesc;
    private int stayMinutes;
    private int age;
    private int workExperience;

    /**
     * Constructor for a worker object.
     *
     * @param jobName     specifies the job title of the worker as a string.
     * @param age         is the age of the worker as an integer.
     * @param stayMinutes is how long the worker will stay at work (in minutes) for per day as an integer.
     */
    public Worker(String jobName, int age, int stayMinutes) {
        this.stayMinutes = stayMinutes;
        if (age <= 20) {
            this.perHour = 11;
        }
        if (age > 20 && age <= 25) {
            this.perHour = 13.50;
        }
        if (age > 25 && age <= 35) {
            this.perHour = 16.50;
        } else if (age > 35) {
            this.perHour = 18.00;
        }

        this.age = age;
        this.workDesc = jobName;
        this.workExperience = 0;
    }

    /**
     * @return salary per hour as an double.
     */
    public double getPerHour() {
        return perHour;
    }

    /**
     * @return the specific worker's description as a string.
     */
    public String getWorkDesc() {
        return workDesc;
    }

}
