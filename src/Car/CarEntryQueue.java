package Car;

public class CarEntryQueue extends CarQueue {

    private int entrySpeed;

    public CarEntryQueue(int entrySpeed) {
        this.entrySpeed = entrySpeed;
    }

    public int getEntrySpeed() {
        return entrySpeed;
    }
}
