package ru.moysklad.util;

public class BankUtils {

    public static boolean validateAccount(String id) {
        return id.matches("\\d{5}");
    }

    public static boolean validateSum(String sum) {
        return sum.matches("\\d+");
    }

}
