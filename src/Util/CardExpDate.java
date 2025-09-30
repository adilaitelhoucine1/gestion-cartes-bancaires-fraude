package Util;

import java.time.LocalDate;

public class CardExpDate {

    public static LocalDate generateExpirationDate(int yearsValid) {
        return LocalDate.now().plusYears(yearsValid);
    }
}
