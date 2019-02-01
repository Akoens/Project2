package Car;

import java.util.LinkedList;
import java.util.Queue;

public abstract class CarQueue {

    private Queue<Car> queue = new LinkedList<>();

    public void addCar(Car car) {
        queue.add(car);
    }

    public Car removeCar() {
        return queue.poll();
    }

    public int carsInQueue(){
        return queue.size();
    }
}
