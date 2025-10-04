package Service;

import DAO.AlertDAO;
import DAO.CardDAO;
import DAO.OperationDAO;
import Entity.Card;
import Entity.CardOperation;
import Entity.FraudAlert;
import Util.FraudRules;

import java.time.LocalDateTime;
import java.util.List;

public class FraudService {

    private  CardDAO cardDAO;
    private  OperationDAO operationDAO;
    private AlertDAO alertDAO;

    public FraudService(CardDAO cardDAO, OperationDAO operationDAO, AlertDAO alertDAO) {
        this.cardDAO=cardDAO;
        this.operationDAO=operationDAO;
        this.alertDAO=alertDAO;
    }
    public void analyseFraud() {
        List<Card> cards = cardDAO.getAllCards();
        for (Card card : cards) {
            List<CardOperation> ops = operationDAO.getAllOperations(card.getId());

            if (FraudRules.checkFraud(ops)) {
                FraudAlert alert = new FraudAlert(
                        1L,
                        "Transaction suspecte dans deux pays différents à 2 minutes d’intervalle",
                        FraudAlert.AlertLevel.CRITICAL,
                        ops.get(0).cardId(),
                        LocalDateTime.now()
                );
                alertDAO.saveAlerte(alert);
               cardDAO.updateStatus(card.getId(), "BLOCKED");
            }
        }
    }
}
