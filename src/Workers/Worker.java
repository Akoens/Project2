package Workers;

public class Worker {
    private String firstName;
    private String lastName;
    private double perHour;
    private String workDesc;
    private int age;
    private int workExperience;

    public Worker(String firstName, String lastName, double perHour, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.perHour = perHour;
        this.age = age;
        this.workDesc = "A parking garage worker";
        this.workExperience = 0;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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
