package Entity;

import java.time.LocalDateTime;
import java.util.List;

public record CardOperation(
        Long id,
        LocalDateTime date,
        double amount,
        OperationType type,
        String location,
        Long cardId
) {
    public enum OperationType { PURCHASE, WITHDRAWAL, ONLINE_PAYMENT }

    public  void getCardByOperation(Card card){
        List<Card>

    }
}