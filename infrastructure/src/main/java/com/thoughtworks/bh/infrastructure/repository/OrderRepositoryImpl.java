package com.thoughtworks.bh.infrastructure.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.thoughtworks.bh.domain.common.BaseException;
import com.thoughtworks.bh.domain.tickets.model.OrderEntity;
import com.thoughtworks.bh.domain.tickets.model.TicketEntity;
import com.thoughtworks.bh.domain.tickets.repository.OrderRepository;
import com.thoughtworks.bh.infrastructure.repository.dataobject.OrderDO;
import com.thoughtworks.bh.infrastructure.repository.dataobject.QOrderDO;
import com.thoughtworks.bh.infrastructure.repository.dataobject.TicketDO;
import com.thoughtworks.bh.infrastructure.repository.jpa.OrderRepo;
import com.thoughtworks.bh.infrastructure.repository.jpa.TicketRepo;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.thoughtworks.bh.infrastructure.mapper.OrderMapper.MAPPER;
import static com.thoughtworks.bh.infrastructure.repository.dataobject.QTicketDO.ticketDO;


@Repository
public class OrderRepositoryImpl implements OrderRepository {
    private final JPAQueryFactory jpaQueryFactory;
    private final OrderRepo orderRepo;
    private final TicketRepo ticketRepo;


    public OrderRepositoryImpl(EntityManager entityManager, OrderRepo orderRepo, TicketRepo ticketRepo) {
        this.jpaQueryFactory = new JPAQueryFactory(entityManager);
        this.orderRepo = orderRepo;
        this.ticketRepo = ticketRepo;
    }

    @Override
    public OrderEntity getById(Long id) {
        OrderDO orderDo = jpaQueryFactory
            .select(QOrderDO.orderDO)
            .from(QOrderDO.orderDO)
            .where(QOrderDO.orderDO.id.eq(id)).fetchOne();
        if (Objects.isNull(orderDo)) {
            throw new BaseException(500, "cannot find order by id:" + id);
        }

        List<TicketDO> tickets = jpaQueryFactory.select(ticketDO)
            .from(ticketDO)
            .where(ticketDO.orderId.eq(id)).fetch();

        OrderEntity order = MAPPER.toOrderDomain(orderDo);
        List<TicketEntity> items = tickets.stream().map(MAPPER::toTicketDomain).collect(Collectors.toList());
        order.setItems(items);
        return order;
    }

    @Override
    public void save(OrderEntity order) {
        OrderDO orderDO = MAPPER.toOrderDo(order);
        orderRepo.save(orderDO);
        order.setId(orderDO.getId());
        List<TicketDO> tickets = order.getItems().stream()
            .map(MAPPER::toTicketDo)
            .peek(ticket -> ticket.setOrderId(orderDO.getId()))
            .collect(Collectors.toList());
        ticketRepo.saveAll(tickets);
    }


}
