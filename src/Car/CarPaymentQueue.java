package Car;

public class CarPaymentQueue extends CarQueue {

    private int paymentSpeed;

    public CarPaymentQueue(int paymentSpeed){
        this.paymentSpeed = paymentSpeed;
    }

    public int getPaymentSpeed() {return paymentSpeed;}
}
