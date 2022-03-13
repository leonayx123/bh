package com.thoughtworks.bh.domain.tickets.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class OrderEntity {
    private Long id;
    private String proposalId;
    private BigDecimal totalAmount;
    private String flightNo;

    private List<TicketEntity> items;

    private LocalDateTime createAt;

    public void withProposalId(String proposalId) {
        this.proposalId = proposalId;
    }
}
