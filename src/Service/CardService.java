package Service;

import DAO.CardDAO;
import Entity.Card;
import Entity.CreditCard;
import Entity.DebitCard;
import Entity.PrepaidCard;
import Util.CardExpDate;
import Util.CardNumberGenerator;

public class CardService {
   private CardDAO cardDAO;


   public CardService(CardDAO cardDAO){
       this.cardDAO=cardDAO;
   }

    public  void issueDebitCard(int clientId , String type , Double dailyLimit){
        DebitCard debitCard = new DebitCard(0, CardExpDate.generateExpirationDate(),
                Card.CardStatus.ACTIVE,clientId,dailyLimit);
          cardDAO.saveDebitCard(debitCard);
    }
    public void issueCreditCard(int clientId , String type, Double monthlyLimit, double interestRate){
        CreditCard creditCard=new CreditCard(0,CardExpDate.generateExpirationDate(),
                Card.CardStatus.ACTIVE,clientId,monthlyLimit,interestRate);
        cardDAO.saveCreditCard(creditCard);
    }
    public void issuePrepaidCard(int clientId , String type,Double availableBalance){
        PrepaidCard  prepaidCard=new PrepaidCard(0,CardExpDate.generateExpirationDate(),
                Card.CardStatus.ACTIVE,clientId,availableBalance);
        cardDAO.savePrepaidCard(prepaidCard);
    }
}
