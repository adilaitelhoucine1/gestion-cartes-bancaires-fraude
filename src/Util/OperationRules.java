package Util;

import DAO.CardDAO;
import DAO.OperationDAO;
import Entity.*;

public class OperationRules {

    private OperationDAO operationDAO;
    private CardDAO cardDAO;

    public boolean validateOperation(Card card, CardOperation cardOperation) {
        if (cardOperation.amount() <= 0) {
            System.out.println("Enter a positive value for amount");
            return false;
        }

        switch (cardDAO.getCardType(card)) {
            case "DEBIT" -> {
                if (card instanceof DebitCard debitCard) {
                    return validateDebitCard(debitCard, cardOperation);
                }
            }
            case "CREDIT" -> {
                if (card instanceof CreditCard creditCard) {
                    return validateCreditCard(creditCard, cardOperation);
                }
            }
            case "PREPAID" -> {
                if (card instanceof PrepaidCard prepaidCard) {
                    return validatePrepaidCard(prepaidCard, cardOperation);
                }
            }
            default -> {
                System.out.println("Unknown card type");
                return false;
            }
        }
        return false;
    }

    public boolean validateDebitCard(DebitCard debitCard, CardOperation cardOperation) {
        double todayTotal = operationDAO.getSumOperationPerToday(debitCard.getId());
        double dailyLimit = debitCard.getDailyLimit();
        if ((todayTotal + cardOperation.amount()) > dailyLimit) {
            System.out.println("You have exceeded the daily limit. Try again tomorrow.");
            return false;
        }
        return true;
    }

    public boolean validateCreditCard(CreditCard creditCard, CardOperation cardOperation) {
        double monthTotal = operationDAO.getSumOperationPerMonth(creditCard.getId());
        if((monthTotal+cardOperation.amount())>creditCard.getMonthlyLimit()){
            System.out.println("You have exceeded the Monthly limit. Try again Next month INshallah.");
            return false;
        }
        return true;
    }

    public  boolean validatePrepaidCard(PrepaidCard prepaidCard, CardOperation cardOperation){
        return  prepaidCard.getAvailableBalance()>=cardOperation.amount();
    }
}