package org.banking.mybankingapplication.model.enums;

public enum Currency {

    /**
     * Change fields to String ??
     * */

    DOLLAR(1),
    EURO(2),
    TURKISH_LIRA(3),
    POUND(4);

    Currency(int num) {}

    Currency chooseCurrency(int type){

        return switch (type) {
            case 1 -> Currency.DOLLAR;
            case 2 -> Currency.EURO;
            case 3 -> Currency.TURKISH_LIRA;
            case 4 -> Currency.POUND;
            default -> throw new RuntimeException("Currency is not present in data"); //Throw custom exception
        };
    }


}


