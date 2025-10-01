package Entity;

import java.time.LocalDate;

public final class PrepaidCard extends Card {
    private final double availableBalance;

    public PrepaidCard(int id, String number, LocalDate expirationDate, CardStatus status, int clientId, double availableBalance) {
        super(id, expirationDate, status, clientId);
        this.availableBalance = availableBalance;
    }

    public double getAvailableBalance() { return availableBalance; }
}