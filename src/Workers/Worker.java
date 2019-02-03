package Workers;

public class Worker {
    private double perHour;
    private String workDesc;
    private int stayMinutes;
    private int age;
    private int workExperience;

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

    public int getStayMinutes() {
        return stayMinutes;
    }

    public void setStayMinutes(int stayMinutes) {
        this.stayMinutes = stayMinutes;
    }
}
