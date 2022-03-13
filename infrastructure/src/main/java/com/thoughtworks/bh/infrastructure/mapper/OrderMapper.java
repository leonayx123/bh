package com.thoughtworks.bh.infrastructure.mapper;

import com.thoughtworks.bh.domain.tickets.model.OrderEntity;
import com.thoughtworks.bh.domain.tickets.model.TicketEntity;
import com.thoughtworks.bh.infrastructure.repository.dataobject.OrderDO;
import com.thoughtworks.bh.infrastructure.repository.dataobject.TicketDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public abstract class OrderMapper {
    public static OrderMapper MAPPER = Mappers.getMapper(OrderMapper.class);

    public abstract OrderEntity toOrderDomain(OrderDO orderDO);

    public abstract TicketEntity toTicketDomain(TicketDO ticketDO);

    public abstract OrderDO toOrderDo(OrderEntity order);

    public abstract TicketDO toTicketDo(TicketEntity ticket);
}
