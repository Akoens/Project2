package Car;

public class CarController {

    private Car car;
    private CarView view;

    /**
     * Constructor for the CarController with two parameters.
     * @param car  a car object.
     * @param view a CarView object.
     */
    public CarController(Car car, CarView view) {
        this.car = car;
        this.view = view;
    }

    /**
     * Method that updates the drawn field.
     */
    public void updateView() {
        view.updateView(car);
    }

}
