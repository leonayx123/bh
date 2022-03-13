package com.thoughtworks.bh.domain.tickets.repository;

import com.thoughtworks.bh.domain.tickets.model.OrderEntity;

public interface OrderRepository {

    OrderEntity getById(Long id);

    void save(OrderEntity order);
}
