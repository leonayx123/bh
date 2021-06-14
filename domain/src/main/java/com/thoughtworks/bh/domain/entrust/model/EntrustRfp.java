package com.thoughtworks.bh.domain.entrust.model;

import lombok.*;

import java.math.BigDecimal;

/**
 * @Description
 * @Author yangxun xun.yang@thoughtworks.com
 * @Version V1.0.0
 * @Since 1.0
 * @Date 2021/6/14
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EntrustRfp {
    private String id;
    private String name;
    private String time;
    private GoodsType type;
    private BigDecimal requirePrice;
    private String phone;
    private String userName;
}
