package com.thoughtworks.bh.infrastructure.entity;

import com.thoughtworks.bh.infrastructure.common.BasePo;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import java.math.BigDecimal;

/**
 * @Description
 * @Author yangxun xun.yang@thoughtworks.com
 * @Version V1.0.0
 * @Since 1.0
 * @Date 2021/6/14
 */
@Entity
@Getter
@Setter
public class EntrustRequestEntity extends BasePo {
    private String id;
    private String name;
    private String time;
    private String type;
    private BigDecimal requirePrice;
    private String phone;
    private String userName;
}
