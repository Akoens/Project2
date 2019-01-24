package Car;

public class CarExitQueue extends CarQueue {

    private int paymentSpeed;
    private int exitSpeed;

    public CarExitQueue(int paymentSpeed, int exitSpeed) {
        this.paymentSpeed = paymentSpeed;
        this.exitSpeed = exitSpeed;
    }

    public int getPaymentSpeed() {
        return paymentSpeed;
    }

    public int getExitSpeed() {
        return exitSpeed;
    }
}
