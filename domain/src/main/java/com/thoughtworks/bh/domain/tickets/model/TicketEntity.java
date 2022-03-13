package com.thoughtworks.bh.domain.tickets.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class TicketEntity {

    private String name;

    private String phone;

    private String idCard;

    private BigDecimal price;

    private Boolean needInsurance;

    private BigDecimal baggageWeightExpense;
}
