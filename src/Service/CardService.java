package Service;

import DAO.CardDAO;
import Entity.*;
import Util.CardExpDate;

import java.util.List;
import java.util.Optional;

public class CardService {
    private CardDAO cardDAO;


    public CardService(CardDAO cardDAO) {
        this.cardDAO = cardDAO;
    }

    public void issueDebitCard(int clientId, String type, Double dailyLimit) {
        DebitCard debitCard = new DebitCard(0, CardExpDate.generateExpirationDate(),
                Card.CardStatus.ACTIVE, clientId, dailyLimit);
        cardDAO.saveDebitCard(debitCard);
    }

    public void issueCreditCard(int clientId, String type, Double monthlyLimit, double interestRate) {
        CreditCard creditCard = new CreditCard(0, CardExpDate.generateExpirationDate(),
                Card.CardStatus.ACTIVE, clientId, monthlyLimit, interestRate);
        cardDAO.saveCreditCard(creditCard);
    }

    public void issuePrepaidCard(int clientId, String type, Double availableBalance) {
        PrepaidCard prepaidCard = new PrepaidCard(0, CardExpDate.generateExpirationDate(),
                Card.CardStatus.ACTIVE, clientId, availableBalance);
        cardDAO.savePrepaidCard(prepaidCard);
    }

    public Optional<Card> findByNumber(String number) {
        List<Card> cards = cardDAO.getAllCards();
        for (Card card : cards) {
            if (card.getNumber().equals(number)) {
                return Optional.of(card);
            }
        }
        return Optional.empty();

    }

    public Optional<Card> findById(CardOperation cardOperation) {
        List<Card> cards = cardDAO.getAllCards();
        for (Card card : cards) {
            if (card.getId()==cardOperation.cardId()){
                return  Optional.of(card);
            }
        }
        return Optional.empty();

    }



    public  Card findCardByID(int id){
        List<Card> cards = cardDAO.getAllCards();
        for (Card card :cards){
            if(card.getId()==id){
                return  card;
            }
        }
        return  null;
    }
    public  void updateStatus(int cardId , String status){
        cardDAO.updateStatus(cardId,status);
    }

}

