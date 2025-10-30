package model;

import model.enums.PaymentMethod;
import java.time.LocalDate;

public class Payment {
    private Rental rental;
    private double amount;
    private LocalDate paymentDate;
    private PaymentMethod method;

    public Payment(Rental rental, double amount, LocalDate paymentDate, PaymentMethod method) {
        this.rental = rental;
        this.amount = amount;
        this.paymentDate = paymentDate;
        this.method = method;
    }

    public Rental getRental() { return rental; }

    @Override
    public String toString() {
        return "Payment{" +
                "rental=" + rental +
                ", amount=" + amount +
                ", paymentDate=" + paymentDate +
                ", method=" + method +
                '}';
    }
}
