package Service;

import DAO.OperationDAO;
import Entity.Card;
import Entity.CardOperation;
import Util.CardExpDate;
import Util.OperationRules;

import java.util.List;

public class OperationService {

    private OperationDAO operationDAO;
    private  CardService cardService;
    private  OperationRules operationRules;
    public OperationService(OperationDAO operationDAO , CardService cardService,OperationRules operationRules){
        this.operationDAO=operationDAO;
        this.cardService=cardService;
        this.operationRules=operationRules;
    }

    public boolean addOperation(CardOperation cardOperation) {
        Card card = cardService.findCardByID(cardOperation.cardId());
        if (card == null) {
            System.out.println("Card not found.");
            return false;
        }
        if (!card.getStatus().equals(Card.CardStatus.ACTIVE)) {
            System.out.println("Ur card is blocked or suspended");
            return false;
        }
        if (CardExpDate.isExpired(cardOperation.date())) {
            System.out.println("Card Expired");
            return false;
        }
        if (!operationRules.validateOperation(card, cardOperation)) {
            return false;
        }

        operationDAO.addOperation(cardOperation);

        return true;
    }

    public List<CardOperation> getHistory(int cardId) {

        Card card = cardService.findCardByID(cardId);
        if (card == null) {
            System.out.println("Card not found.");

        }
        return operationDAO.getAllOperations(cardId);

    }

}
