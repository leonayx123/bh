package com.thoughtworks.bh.domain.entrust.model;

import com.thoughtworks.bh.domain.common.BaseException;
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
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Entrust {
    private EntrustRfp entrustRfp;
    private String id;
    private BigDecimal evaluationPrice;
    private BigDecimal dealPrice;
    private EntrustStatus status;

    public void cancel() {
        if (this.status == null || !this.status.equals(EntrustStatus.UN_AUCTION)) {
            throw new BaseException(500, "状态已经无法撤销");
        }
        this.status = EntrustStatus.CANCELED;
    }
}
