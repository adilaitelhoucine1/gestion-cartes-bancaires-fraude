package Entity;

import java.time.LocalDate;

public final class DebitCard extends Card {
    private final double dailyLimit;

    public DebitCard(Long id, String number, LocalDate expirationDate, CardStatus status, Long clientId, double dailyLimit) {
        super(id, number, expirationDate, status, clientId);
        this.dailyLimit = dailyLimit;
    }

    public double getDailyLimit() { return dailyLimit; }
}