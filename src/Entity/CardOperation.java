package Entity;

import java.time.LocalDateTime;

public record CardOperation(
        Long id,
        LocalDateTime date,
        double amount,
        OperationType type,
        String location,
        Long cardId
) {
    public enum OperationType { PURCHASE, WITHDRAWAL, ONLINE_PAYMENT }
}