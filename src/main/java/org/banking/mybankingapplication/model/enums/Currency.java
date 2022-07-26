package org.banking.mybankingapplication.model.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.engine.jdbc.Size;

import java.util.Arrays;
import java.util.Locale;

@ToString
@Getter
//@Setter //doesn't work

public enum Currency {

    /**
     * Change fields to String ??
     * */

    DOLLAR(1),
    EURO(2),
    POUND(3),
    TURKISH_LIRA(4);


    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    int i;
    Currency(int i) {
        this.i = i;
    }

    String getText(){
        return this.name();
    }

    @JsonCreator
    public static Currency intToEnum(int number){
        //If enums are ordered
        Currency currency = null;
        try{
            currency = Currency.values()[number];
        }
        catch(Exception e){
            throw new IllegalArgumentException("Currency is not present in data");
        }
        return currency;

    }
    public static int EnumToInt(Currency currency){
        //If enums are ordered
        return currency.ordinal();
    }

    public static Currency EnumToIntSwitch(int number){

        return switch (number) {
            case 1-> Currency.DOLLAR;
            case 2 -> Currency.EURO;
            case 3 -> Currency.TURKISH_LIRA;
            case 4 -> Currency.POUND;
            default -> throw new IllegalArgumentException("Currency is not present in data");
        };
    }

}




