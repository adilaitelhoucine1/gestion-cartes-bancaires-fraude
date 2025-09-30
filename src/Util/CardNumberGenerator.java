package Util;

import java.util.Random;

public class CardNumberGenerator {

    public static String generateCardNumber() {
        Random random = new Random();
        StringBuilder cardNumber = new StringBuilder("4000");

        for (int i = 0; i < 12; i++) {
            cardNumber.append(random.nextInt(10));
        }

        return cardNumber.toString();
    }
}
