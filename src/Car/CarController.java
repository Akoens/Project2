package Car;

public class CarController {

    private Car car;
    private CarView view;

    /**
     * @param car  a car object.
     * @param view a CarView object.
     */
    public CarController(Car car, CarView view) {
        this.car = car;
        this.view = view;
    }

    /**
     * A method that updates the drawn field every time it's called.
     */
    public void updateView() {
        view.updateView(car);
    }

}
