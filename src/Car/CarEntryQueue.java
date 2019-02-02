package Car;

public class CarEntryQueue extends CarQueue {

    private int entrySpeed;

    /**
     * Constructor for the CarEntryQueue with one parameter.
     * @param entrySpeed an integer deciding the speed of cars enter the garage.
     */
    public CarEntryQueue(int entrySpeed) {
        this.entrySpeed = entrySpeed;
    }

    /**
     * @return the integer of the entry speed.
     */
    public int getEntrySpeed() {
        return entrySpeed;
    }
}
