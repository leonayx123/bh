package com.thoughtworks.bh.domain.entrust.model;

import com.thoughtworks.bh.domain.common.BaseException;

import java.util.Arrays;

/**
 * @Description
 * @Author yangxun xun.yang@thoughtworks.com
 * @Version V1.0.0
 * @Since 1.0
 * @Date 2021/6/14
 */
public enum GoodsType {
    ANTIQUE("古董"), ARTWORK("艺术品");

    private String code;

    GoodsType(String code) {
        this.code = code;
    }

    public static GoodsType byCode(String code) {
        return Arrays.stream(GoodsType.values())
                .filter(goodsType -> goodsType.code.equals(code))
                .findFirst().orElseThrow(() -> new BaseException(400, "不支持的拍品类型"));
    }
}
