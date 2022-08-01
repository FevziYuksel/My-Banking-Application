package org.banking.mybankingapplication.exception;

import java.util.List;

public class ErrorResponse {

    private String message;
    private List<String> details;


    public ErrorResponse(String message, List<String> details) {
        super(); //Not sure why it is existed
        this.details = details;
        this.message = message;
    }
}
