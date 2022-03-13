package com.thoughtworks.bh.domain.tickets.client;

import com.thoughtworks.bh.domain.tickets.model.OrderEntity;

public interface TicketsInventoryClient {

    boolean reserved(OrderEntity order);
}
