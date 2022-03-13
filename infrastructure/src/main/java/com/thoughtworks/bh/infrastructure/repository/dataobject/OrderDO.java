package com.thoughtworks.bh.infrastructure.repository.dataobject;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "t_order")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDO extends BaseDO {
    private String proposalId;
    private BigDecimal totalAmount;
    private String flightNo;
}
