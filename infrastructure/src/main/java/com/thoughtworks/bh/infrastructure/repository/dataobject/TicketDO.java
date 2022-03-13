package com.thoughtworks.bh.infrastructure.repository.dataobject;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "t_ticket")
@Getter
@Setter
public class TicketDO extends BaseDO {
    private Long orderId;

    private String name;

    private String phone;

    private String idCard;

    private BigDecimal price;

    private Boolean needInsurance;

    private BigDecimal baggageWeightExpense;
}
