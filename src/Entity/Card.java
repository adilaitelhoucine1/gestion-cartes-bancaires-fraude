package Entity;

import java.time.LocalDate;

public sealed class Card permits DebitCard, CreditCard, PrepaidCard {
    private final Long id;
    private final String number;
    private final LocalDate expirationDate;
    private final CardStatus status;
    private final Long clientId;

    protected Card(Long id, String number, LocalDate expirationDate, CardStatus status, Long clientId) {
        this.id = id;
        this.number = number;
        this.expirationDate = expirationDate;
        this.status = status;
        this.clientId = clientId;
    }

    public Long getId() { return id; }
    public String getNumber() { return number; }
    public LocalDate getExpirationDate() { return expirationDate; }
    public CardStatus getStatus() { return status; }
    public Long getClientId() { return clientId; }

    public enum CardStatus { ACTIVE, SUSPENDED, BLOCKED }
}