package Car;

public class CarExitQueue extends CarQueue {

    private int exitSpeed;

    public CarExitQueue(int exitSpeed) {
        this.exitSpeed = exitSpeed;
    }

    public int getExitSpeed() {
        return exitSpeed;
    }
}
