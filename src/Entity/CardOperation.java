package Entity;

import java.time.LocalDateTime;
import java.util.List;

public record CardOperation(
        Long id,
        LocalDateTime date,
        double amount,
        OperationType type,
        String location,
        int cardId
) {
    public enum OperationType { PURCHASE, WITHDRAWAL, ONLINE_PAYMENT }


}