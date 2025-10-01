package Util;

import java.time.LocalDate;

public class CardExpDate {

    public static LocalDate generateExpirationDate() {
        return LocalDate.now().plusYears(5);
    }
}
