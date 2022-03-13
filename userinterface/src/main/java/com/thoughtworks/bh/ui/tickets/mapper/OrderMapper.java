package com.thoughtworks.bh.ui.tickets.mapper;

import com.thoughtworks.bh.domain.tickets.model.OrderEntity;
import com.thoughtworks.bh.domain.tickets.model.TicketEntity;
import com.thoughtworks.bh.ui.tickets.request.CreateOrderRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public abstract class OrderMapper {
    public static OrderMapper MAPPER = Mappers.getMapper(OrderMapper.class);

    public abstract OrderEntity requestToDomain(CreateOrderRequest request);

    public abstract TicketEntity itemToTicket(CreateOrderRequest.OrderItem orderItem);
}
