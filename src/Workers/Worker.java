package Workers;

public class Worker {
    private double perHour;
    private String workDesc;
    private int stayMinutes;
    private int age;
    private int workExperience;

    public Worker(String jobName, int age, int stayMinutes) {
        this.stayMinutes = stayMinutes;
        this.perHour = age * 0.65;
        this.age = age;
        this.workDesc = jobName;
        this.workExperience = 0;
    }


    public double getPerHour() {
        return perHour;
    }

    public void setPerHour(double perHour) {
        this.perHour = perHour;
    }

    public String getWorkDesc() {
        return workDesc;
    }

    public void setWorkDesc(String workDesc) {
        this.workDesc = workDesc;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getWorkExperience() {
        return workExperience;
    }

    public void setWorkExperience(int workExperience) {
        this.workExperience = workExperience;
    }
}
