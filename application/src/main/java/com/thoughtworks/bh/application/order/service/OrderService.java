package com.thoughtworks.bh.application.order.service;

import com.thoughtworks.bh.domain.tickets.client.TicketsInventoryClient;
import com.thoughtworks.bh.domain.tickets.model.OrderEntity;
import com.thoughtworks.bh.domain.tickets.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final TicketsInventoryClient ticketsInventoryClient;

    public boolean createOrder(OrderEntity order) {
        boolean reservedSuccess = ticketsInventoryClient.reserved(order);
        if (reservedSuccess) {
            orderRepository.save(order);
            return true;
        }
        return false;
    }
}
