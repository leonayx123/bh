package com.thoughtworks.bh.application.order.service

import com.alibaba.fastjson.JSON
import com.thoughtworks.bh.domain.tickets.client.TicketsInventoryClient
import com.thoughtworks.bh.domain.tickets.model.OrderEntity
import com.thoughtworks.bh.domain.tickets.repository.OrderRepository
import spock.lang.Specification

class OrderServiceTest extends Specification {
    OrderRepository orderRepository = Mock()
    TicketsInventoryClient ticketsInventoryClient = Mock()

    OrderService orderService

    void setup() {
        orderService = new OrderService(orderRepository, ticketsInventoryClient)
    }

    def "should_create_order_happy_path"() {
        given:
        ticketsInventoryClient.reserved(_ as OrderEntity) >> true

        OrderEntity order = buildOrderForTest()
        when:
        def result = orderService.createOrder(order)
        then:
        1 * orderRepository.save({
            verifyAll(it, OrderEntity) {
                it.flightNo == "MU2111"
                it.items[0].name == "杨勋"
                it.items[0].phone == "15690801558"
                it
            }
            it
        } as OrderEntity)
        result == true
    }


    def "should return false when TicketsInventoryClient reserved false"() {
        given:
        ticketsInventoryClient.reserved(_ as OrderEntity) >> false

        OrderEntity order = buildOrderForTest()
        when:
        def result = orderService.createOrder(order)

        then:
        result == false
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
