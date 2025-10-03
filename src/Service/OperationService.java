package Service;

import DAO.OperationDAO;
import Entity.Card;
import Entity.CardOperation;
import Util.CardExpDate;

import java.time.LocalDate;

public class OperationService {

    private OperationDAO operationDAO;
    private  CardService cardService;
    public OperationService(OperationDAO operationDAO , CardService cardService){
        this.operationDAO=operationDAO;
        this.cardService=cardService;
    }

    public  boolean addOperation(CardOperation cardOperation){
       if(cardService.findById(cardOperation).isPresent()){
           Card card=cardService.findById(cardOperation).get();
           if(card.getStatus().equals(Card.CardStatus.ACTIVE)){
               if(CardExpDate.isExpired(cardOperation.date())){
                  //  if()
               }else{
                   System.out.println("Card Expired");
                   return false;
               }
           }else{
               System.out.println("Ur card is blocked or suspended");
               return  false;
           }
       }
        return false;
    }

}
