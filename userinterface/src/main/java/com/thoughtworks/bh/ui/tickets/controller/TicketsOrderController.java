package com.thoughtworks.bh.ui.tickets.controller;

import com.thoughtworks.bh.application.order.service.OrderService;
import com.thoughtworks.bh.domain.tickets.model.OrderEntity;
import com.thoughtworks.bh.ui.common.CommonResponse;
import com.thoughtworks.bh.ui.tickets.mapper.OrderMapper;
import com.thoughtworks.bh.ui.tickets.request.CreateOrderRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class TicketsOrderController {

    private final OrderService orderService;

    @PostMapping("/air-ticket/proposals/{pid}/orders")
    public CommonResponse<Void> createOrder(@PathVariable("pid") String proposalId, @RequestBody @Valid CreateOrderRequest createOrderRequest) {

        OrderEntity orderEntity = OrderMapper.MAPPER.requestToDomain(createOrderRequest);
        orderEntity.withProposalId(proposalId);
        orderService.createOrder(orderEntity);

        // 异常处理已经在全局的 ExceptionHandle 处理.无需每个controller单独实现.这里和工序写的不一样
        // 工序主要为打样. 实际开发会使用全局异常处理.
        // 自定义Exception且继承BaseException之后,设置code 为需要返回的http status即可
        // 全局ExceptionHandle会对BaseException进行处理
        return CommonResponse.success("下单成功");
    }
}
