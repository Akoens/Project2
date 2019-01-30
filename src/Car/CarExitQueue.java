package Car;

public class CarExitQueue extends CarQueue {

    private int exitSpeed;

    /**
     * Constructor for the CarExitQueue with one parameter.
     *
     * @param exitSpeed an integer that decides the speed of the CarExitQueue.
     */
    public CarExitQueue(int exitSpeed) {
        this.exitSpeed = exitSpeed;
    }

    /**
     *
     * @return the exit speed integer of the CarExitQueue.
     */
    public int getExitSpeed() {
        return exitSpeed;
    }
}
