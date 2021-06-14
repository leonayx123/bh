package com.thoughtworks.bh.infrastructure.entity;

import com.thoughtworks.bh.infrastructure.common.BasePo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class EntrustEntity extends BasePo {
    private BigDecimal evaluationPrice;
    private BigDecimal dealPrice;
    private String status;
}
