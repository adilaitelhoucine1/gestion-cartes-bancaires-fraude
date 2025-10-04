package Util;

import DAO.CardDAO;
import DAO.OperationDAO;
import Entity.*;

public class OperationRules {

    private OperationDAO operationDAO;
    private CardDAO cardDAO;

    public OperationRules(OperationDAO operationDAO , CardDAO cardDAO){
        this.operationDAO=operationDAO;
        this.cardDAO=cardDAO;
    }

    public boolean validateOperation(Card card, CardOperation cardOperation) {
        if (cardOperation.amount() <= 0) {
            System.out.println("Enter a positive value for amount");
            return false;
        }
        System.out.println("start switch");

        switch (cardDAO.getCardType(card)) {
            case "DEBIT" -> {
                if (card instanceof DebitCard debitCard) {

                    return validateDebitCard(debitCard, cardOperation);
                }else{
                    System.out.println("eeeeeee");
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
        System.out.println("enter heeeeeeeere");
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
            System.out.println("You have exceeded the Monthly limit. Try again next month Inshallah.");
            return false;
        }
        return true;
    }

    public boolean validatePrepaidCard(PrepaidCard prepaidCard, CardOperation cardOperation){
        if(prepaidCard.getAvailableBalance() < cardOperation.amount()){
            System.out.println("Insufficient balance on prepaid card.");
            return false;
        }
        return true;
    }
}