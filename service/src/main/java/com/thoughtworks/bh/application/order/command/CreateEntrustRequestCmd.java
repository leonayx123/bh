package com.thoughtworks.bh.application.order.command;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateEntrustRequestCmd {
    private String id;
    private String name;
    private String time;
    private String type;
    private BigDecimal requirePrice;
    private String phone;
    private String userName;
}
