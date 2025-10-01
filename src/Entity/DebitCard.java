package Entity;

import java.time.LocalDate;

public final class DebitCard extends Card {
    private final double dailyLimit;

    public DebitCard(int id, LocalDate expirationDate, CardStatus status, int clientId, double dailyLimit) {
        super(id, expirationDate, status, clientId);
        this.dailyLimit = dailyLimit;
    }

    public double getDailyLimit() { return dailyLimit; }
}