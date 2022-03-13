package com.thoughtworks.bh.infrastructure.restclient.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ReservedParamDto {
    private String flightNo;
    private Integer ticketNum;
}
