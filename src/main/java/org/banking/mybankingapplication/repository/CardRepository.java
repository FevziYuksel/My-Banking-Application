package org.banking.mybankingapplication.repository;

import org.banking.mybankingapplication.model.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository< Card,Long >{

}
