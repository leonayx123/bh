package com.thoughtworks.bh.infrastructure.repository

import com.alibaba.fastjson.JSON
import com.thoughtworks.bh.JpaSpecification
import com.thoughtworks.bh.domain.tickets.model.OrderEntity
import com.thoughtworks.bh.domain.tickets.repository.OrderRepository
import com.thoughtworks.bh.infrastructure.repository.dataobject.OrderDO
import com.thoughtworks.bh.infrastructure.repository.dataobject.TicketDO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.context.annotation.Import

@Import(OrderRepositoryImpl.class)
class OrderRepositoryImplTest extends JpaSpecification {
    @Autowired
    OrderRepository orderRepository

    @Autowired
    TestEntityManager testEntityManager

    def should_save_order_happy_path() {
        given:
        OrderEntity order = buildOrderForTest()
        when:
        orderRepository.save(order)
        then:
        order.getId() != null

        OrderDO orderDO = testEntityManager.find(OrderDO.class, order.getId())
        orderDO != null
        orderDO.flightNo == order.flightNo
    }

    def should_query_order_by_id() {
        given:
        OrderDO orderDo = new OrderDO(flightNo: "test_f_no")
        testEntityManager.persist(orderDo)

        TicketDO ticketDO = new TicketDO(orderId: orderDo.id, name: "yangxun", phone: "phoneno")
        testEntityManager.persist(ticketDO)
        when:
        def order = orderRepository.getById(orderDo.getId());
        then:

        order.flightNo == orderDo.flightNo
        order.items[0].name == ticketDO.name
        order.items[0].phone == ticketDO.phone
    }


    static OrderEntity buildOrderForTest() {
        String json = "{\n" +
                "            \"flightNo\": \"MU2111\",\n" +
                "            \"items\": [\n" +
                "                {\n" +
                "                    \"idCard\": \"6101131985111111160\",\n" +
                "                    \"name\": \"杨勋\",\n" +
                "                    \"phone\": \"15690801558\",\n" +
                "                    \"baggageWeightExpense\": 0,\n" +
                "                    \"needInsurance\": true,\n" +
                "                    \"price\": 0\n" +
                "                }\n" +
                "        ]\n" +
                "        }"
        return JSON.parseObject(json, OrderEntity.class)
    }
}
