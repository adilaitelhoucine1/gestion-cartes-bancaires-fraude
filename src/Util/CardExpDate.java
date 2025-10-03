package Util;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class CardExpDate {

    public static LocalDate generateExpirationDate() {
        return LocalDate.now().plusYears(5);
    }
    public  static boolean isExpired(LocalDateTime localDateTime){
        return localDateTime.isBefore(LocalDateTime.now());
    }
}
