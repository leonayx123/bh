package com.thoughtworks.bh.domain.entrust.model;

import lombok.Getter;

/**
 * @Description
 * @Author yangxun xun.yang@thoughtworks.com
 * @Version V1.0.0
 * @Since 1.0
 * @Date 2021/6/14
 */
@Getter
public enum EntrustStatus {
    UN_AUCTION, AUCTIONING, AUCTION_DEAL, CANCELED;
}
