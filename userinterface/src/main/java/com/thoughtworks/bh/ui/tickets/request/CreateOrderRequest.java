package com.thoughtworks.bh.ui.tickets.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.List;

@ApiModel("创建订单的请求request")
@Getter
@Setter
@Valid
public class CreateOrderRequest {

    @ApiModelProperty("航班号")
    private String flightNo;


    @ApiModelProperty("订单中的order item列表")
    private List<OrderItem> items;

    @ApiModel(value = "订单行明细")
    @Getter
    @Setter
    @Valid
    @Validated
    public static class OrderItem {
        @ApiModelProperty("乘机人姓名")
        private String name;

        @NotBlank(message = "乘机人电话不能为空")
        @ApiModelProperty("乘机人电话")
        private String phone;

        @NotBlank(message = "乘机人身份证号不能为空")
        @ApiModelProperty("乘机人身份证号")
        private String idCard;

        @ApiModelProperty("机票价格")
        private BigDecimal price;

        @ApiModelProperty("是否需要保险")
        private Boolean needInsurance;

        @ApiModelProperty("行李超重收费")
        private BigDecimal baggageWeightExpense;
    }
}
