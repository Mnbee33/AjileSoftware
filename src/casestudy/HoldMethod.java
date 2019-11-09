package casestudy;

public class HoldMethod implements PaymentMethod {
    @Override
    public void pay(PayCheck pc) {
        System.out.println("Hold Method");
    }
}
