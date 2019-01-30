package Car;

public class CarPaymentQueue extends CarQueue {

    private int paymentSpeed;

    /**
     * Constructor for the CarPaymentQueue with one parameter.
     *
     * @param paymentSpeed
     */
    public CarPaymentQueue(int paymentSpeed){
        this.paymentSpeed = paymentSpeed;
    }

    /**
     *
     * @return the payment speed integer of the CarPaymentQueue.
     */
    public int getPaymentSpeed() {return paymentSpeed;}
}
