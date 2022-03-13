package com.thoughtworks.bh.ui.tickets.controller

import com.thoughtworks.bh.application.order.service.OrderService
import com.thoughtworks.bh.domain.tickets.model.OrderEntity
import com.thoughtworks.bh.ui.common.CommonResponse
import com.thoughtworks.bh.ui.tickets.request.CreateOrderRequest
import spock.lang.Specification

class TicketsOrderControllerTest extends Specification {
    OrderService orderService = Mock()

    TicketsOrderController controller


    void setup() {
        controller = new TicketsOrderController(orderService);
    }

    def "CreateOrder"() {
        given:
        orderService.createOrder(_ as OrderEntity) >> {
            return true
        }

        CreateOrderRequest request = new CreateOrderRequest()

        when:
        CommonResponse response = controller.createOrder("1", request)

        then:
        response.success == true
        response.message == "下单成功"
    }
}
