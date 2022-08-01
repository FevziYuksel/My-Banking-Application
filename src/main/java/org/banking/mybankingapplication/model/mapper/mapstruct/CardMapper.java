package org.banking.mybankingapplication.model.mapper.mapstruct;

import org.banking.mybankingapplication.model.dto.CardDTO;
import org.banking.mybankingapplication.model.dto.CustomerDTO;
import org.banking.mybankingapplication.model.entity.Card;
import org.banking.mybankingapplication.model.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;


@Mapper(componentModel  = "spring")
public interface CardMapper {

    CardDTO toDTO(Card card);

    List<CardDTO> toDTO(List<Card> cardList);

    Card toEntity(CardDTO cardDTO);

    List<Card> toEntity(List<CardDTO> cardDTOList);
}
