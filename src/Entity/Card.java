package Entity;

import Util.CardNumberGenerator;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public sealed class Card permits DebitCard, CreditCard, PrepaidCard {
    private final int id;
    private final String number;
    private final LocalDate expirationDate;
    private final CardStatus status;
    private final int clientId;

    public Card(int id,  LocalDate expirationDate, CardStatus status, int clientId) {
        this.id = id;
        this.number =CardNumberGenerator.generateCardNumber();
        this.expirationDate = expirationDate;
        this.status = status;
        this.clientId = clientId;

    }

    public int getId() { return id; }
    public String getNumber() { return number; }
    public LocalDate getExpirationDate() { return expirationDate; }
    public CardStatus getStatus() { return status; }
    public int getClientId() { return clientId; }

    public enum CardStatus { ACTIVE, SUSPENDED, BLOCKED }

    public static  Card   getCardByNumber(String number){
        List<Card> cards = new ArrayList<>();
        for (Card card : cards){
            if(card.getNumber().equals(number)){
                return card;
            }
        }
        return null;
    }
}