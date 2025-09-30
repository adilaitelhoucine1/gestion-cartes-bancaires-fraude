package Util;
import java.util.regex.Pattern;

public class InputValidator {

    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");

    private static final Pattern PHONE_PATTERN =
            Pattern.compile("^[0-9]{8,15}$");

    private static final Pattern NAME_PATTERN =
            Pattern.compile("^[A-Za-zÀ-ÿ' -]{2,50}$");


    public static boolean isValidName(String name) {
        return name != null && NAME_PATTERN.matcher(name).matches();
    }

    public static boolean isValidEmail(String email) {
        return email != null && EMAIL_PATTERN.matcher(email).matches();
    }

    public static boolean isValidPhone(String phone) {
        return phone != null && PHONE_PATTERN.matcher(phone).matches();
    }


    public static boolean isValidCardNumber(String number) {
        return number != null && number.matches("\\d{16}");
    }

    public static boolean isFutureDate(java.time.LocalDate date) {
        return date != null && date.isAfter(java.time.LocalDate.now());
    }

    public static boolean isNonNegativeAmount(double amount) {
        return amount >= 0;
    }


    public static boolean isValidOperationType(String type) {
        return type != null && (
                type.equalsIgnoreCase("ACHAT") ||
                        type.equalsIgnoreCase("RETRAIT") ||
                        type.equalsIgnoreCase("PAIEMENTENLIGNE")
        );
    }

}
