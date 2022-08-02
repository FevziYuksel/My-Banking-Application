package org.banking.mybankingapplication.model.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.engine.jdbc.Size;

import java.util.Arrays;
import java.util.Locale;


@Getter
//@Setter //doesn't work

public enum Currency {

    /**
     * Integer type can be parsed from json
     * */

    DOLLAR("USD"),
    EURO("EUR"),
    TURKISH_LIRA("TRY"),
    POUND("GBP");

    Currency(String name) {}

    public static Currency StringToEnum(String text) {
        //Text must be exactly same
        Currency currency = null;
        try{
            currency = Currency.valueOf(text.toUpperCase());
        }catch (Exception e){
            throw new IllegalArgumentException("Currency is not present in data");
        }
        return currency;
    }
    public static String EnumToString(Currency currency) {

        String text ;
        try{
            text = currency.name();
        }catch (Exception e){
            throw new IllegalArgumentException("Currency is not present in data");
        }
        return text;
    }


}




