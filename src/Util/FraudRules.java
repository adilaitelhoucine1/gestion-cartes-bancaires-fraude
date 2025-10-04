package Util;

import Entity.Card;
import Entity.CardOperation;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

public class FraudRules {



    public static  boolean checkFraud(List<CardOperation> ops){
            for (CardOperation cardOperation : ops){
                if(cardOperation.amount()>10000){
                    return  true;
                }

                if (checkOperationsInDifferentLocations(ops)) return true;
                if (checkManyOpsInShortTime(ops)) return true;
            }
            return  false;
    }

    public static boolean checkOperationsInDifferentLocations(List<CardOperation> ops) {
        for (int i = 0; i < ops.size(); i++) {
            for (int j = i + 1; j < ops.size(); j++) {
                CardOperation op1 = ops.get(i);
                CardOperation op2 = ops.get(j);
                if (!op1.location().equals(op2.location()) &&
                        Math.abs(java.time.Duration.between(op1.date(), op2.date()).toMinutes()) < 5) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean checkManyOpsInShortTime(List<CardOperation> ops) {

        ops.sort(Comparator.comparing(CardOperation::date));
        for (int i = 0; i < ops.size(); i++) {
            int count = 1;
            LocalDateTime start = ops.get(i).date();
            for (int j = i + 1; j < ops.size(); j++) {
                long minutes = Math.abs(Duration.between(start, ops.get(j).date()).toMinutes());
                if (minutes < 1) {
                    count++;
                    if (count >= 3) {
                        return true;
                    }
                } else {
                    break;
                }
            }
        }
        return false;
    }


    }
