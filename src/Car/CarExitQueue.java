package Car;

public class CarExitQueue extends CarQueue {

    private int exitSpeed;

    /**
     * Constructor for the CarExitQueue with one parameter.
     * @param exitSpeed an integer that decides the speed of cars exiting the garage.
     */
    public CarExitQueue(int exitSpeed) {
        this.exitSpeed = exitSpeed;
    }

    /**
     * @return the integer of the exit speed.
     */
    public int getExitSpeed() {
        return exitSpeed;
    }
}
