package Entity;

import java.time.LocalDate;

public final class CreditCard extends Card {
    private final double monthlyLimit;
    private final double interestRate;

    public CreditCard(Long id, String number, LocalDate expirationDate, CardStatus status, Long clientId, double monthlyLimit, double interestRate) {
        super(id, number, expirationDate, status, clientId);
        this.monthlyLimit = monthlyLimit;
        this.interestRate = interestRate;
    }

    public double getMonthlyLimit() { return monthlyLimit; }
    public double getInterestRate() { return interestRate; }
}