package Car;

import java.util.LinkedList;
import java.util.Queue;

public abstract class CarQueue {

    private Queue<Car> queue = new LinkedList<>();

    public boolean addCar(Car car) {
        return queue.add(car);
    }

    public Car getCar() {
        return queue.poll();
    }

    public void removeCar() { queue.remove(); }

    public int carsInQueue(){
        return queue.size();
    }
}
