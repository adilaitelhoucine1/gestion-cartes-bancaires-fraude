package Service;

import DAO.CardDAO;
import Entity.Card;
import Entity.DebitCard;
import Util.CardExpDate;
import Util.CardNumberGenerator;

public class CardService {
   private CardDAO cardDAO;


   public CardService(CardDAO cardDAO){
       this.cardDAO=cardDAO;
   }

    public  void issueDebitCard(Long clientId , String type , Double dailyLimit){
        DebitCard debitCard = new DebitCard(0, CardNumberGenerator.generateCardNumber(), CardExpDate.generateExpirationDate(),
                "ACTIVE",clientId,dailyLimit);
      //  cardDAO.save(debitCard);
    }
    public void issueCreditCard(){}
    public void issuePrepaidCard(){}
}
