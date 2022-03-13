package com.thoughtworks.bh.infrastructure.repository.jpa;

import com.thoughtworks.bh.infrastructure.repository.dataobject.OrderDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.QuerydslJpaRepository;

public interface OrderRepo extends JpaRepository<OrderDO, Long> {
}
