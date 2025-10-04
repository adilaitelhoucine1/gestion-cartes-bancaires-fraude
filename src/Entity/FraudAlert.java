package Entity;

import java.time.LocalDateTime;

public record FraudAlert(
        Long id,
        String description,
        AlertLevel level,
        int cardId,
        LocalDateTime createdAt
) {
    public enum AlertLevel { INFO, WARNING, CRITICAL }
}