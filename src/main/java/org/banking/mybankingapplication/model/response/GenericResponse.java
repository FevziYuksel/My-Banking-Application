package org.banking.mybankingapplication.model.response;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

public class GenericResponse<T> implements Serializable {

    private LocalDate date;

//    private List<String> details;
//
//    private String detail;
    private T message;

    public GenericResponse(LocalDate date, T message) {
        this.date = LocalDate.now();
        this.message = message;
    }
}
