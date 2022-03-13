package com.thoughtworks.bh.infrastructure.restclient;

import com.thoughtworks.bh.domain.tickets.client.TicketsInventoryClient;
import com.thoughtworks.bh.domain.tickets.model.OrderEntity;
import com.thoughtworks.bh.infrastructure.restclient.dto.ReservedParamDto;
import com.thoughtworks.bh.infrastructure.exception.ReservedException;
import com.thoughtworks.bh.infrastructure.exception.ServiceUnvaliableException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class TicketsInventoryRestClient implements TicketsInventoryClient {

    private final RestTemplate restTemplate;
    private final static String TICKETS_INVENTORY_SERVER_URI = "/tickets-inventory/tickets/reserved";


    @Override
    public boolean reserved(OrderEntity order) {
        ReservedParamDto reservedParamDto = ReservedParamDto.builder()
            .flightNo(order.getFlightNo())
            .ticketNum(order.getItems().size())
            .build();
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(TICKETS_INVENTORY_SERVER_URI, reservedParamDto, String.class);

        if (responseEntity.getStatusCode() == HttpStatus.SERVICE_UNAVAILABLE) {
            throw new ServiceUnvaliableException("预留系统服务不可用");
        } else if (responseEntity.getStatusCode() == HttpStatus.CONFLICT) {
            throw new ReservedException("预留系统服务不可用");
        }
        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            return true;
        }

        return false;
    }
}
