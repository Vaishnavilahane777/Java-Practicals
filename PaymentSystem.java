abstract class Payment{
    public abstract void paymentAmount();
    public void paymentDetails(){
        System.out.println("Payment details are as follows:");
    }
}

class CreditCard extends Payment{
    @Override
    public void paymentAmount() {
        System.out.println("Payment made using Credit Card");
    }
}

class UPI extends Payment{
    @Override
    public void paymentAmount() {
        System.out.println("Payment made using UPI");
    }
}

public class PaymentSystem {
    public static void main(String[] args) {
        Payment payment1 = new CreditCard();
        payment1.paymentDetails();
        payment1.paymentAmount();
        System.out.println();   
        Payment payment2 = new UPI();
        payment2.paymentDetails();
        payment2.paymentAmount();
    }
}