package Car;

import java.util.LinkedList;
import java.util.Queue;

public abstract class CarQueue {

    private Queue<Car> queue = new LinkedList<>();

    /**
     * Method to add a car object into the corresponding queue ArrayList.
     *
     * @param car a car object.
     */
    public void addCar(Car car) {
        queue.add(car);
    }

    /**
     * Method to remove a car object from the corresponding queue ArrayList.
     * @return the head of the queue, or null if the queue is empty.
     */
    public Car removeCar() {
        return queue.poll();
    }

    /**
     * Method to see how big the corresponding queue is.
     * @return the current size of the corresponding queue ArrayList as an integer.
     */
    public int carsInQueue(){
        return queue.size();
    }
}
