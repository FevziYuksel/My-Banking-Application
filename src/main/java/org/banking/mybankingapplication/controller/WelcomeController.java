package org.banking.mybankingapplication.controller;

import org.banking.mybankingapplication.model.response.ResponseModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {

    // http://localhost:8080/URI
    @GetMapping("/welcome")
    public ResponseEntity welcomeMessageApi() {
        String welcomeMsg = "Welcome to Course Management Application";
        ResponseModel responseModel = new ResponseModel();
        responseModel.setWelcomeMessage(welcomeMsg);
        return ResponseEntity.status(200).body(responseModel);
    }


}