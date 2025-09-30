package Entity;

import java.time.LocalDate;

public final class PrepaidCard extends Card {
    private final double availableBalance;

    public PrepaidCard(Long id, String number, LocalDate expirationDate, CardStatus status, Long clientId, double availableBalance) {
        super(id, number, expirationDate, status, clientId);
        this.availableBalance = availableBalance;
    }

    public double getAvailableBalance() { return availableBalance; }
}