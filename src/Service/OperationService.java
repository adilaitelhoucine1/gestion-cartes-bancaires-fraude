package Service;

import DAO.OperationDAO;
import Entity.Card;
import Entity.CardOperation;

public class OperationService {

    private OperationDAO operationDAO;
    private  CardService cardService;
    public OperationService(OperationDAO operationDAO , CardService cardService){
        this.operationDAO=operationDAO;
        this.cardService=cardService;
    }

    public  void addOperation(CardOperation cardOperation){
       if(cardService.findByOperation(cardOperation).isPresent()){
           Card card=cardService.findByOperation(cardOperation).get();
           if(card.getStatus().equals(Card.CardStatus.ACTIVE)){

           }
       }
    }
}
