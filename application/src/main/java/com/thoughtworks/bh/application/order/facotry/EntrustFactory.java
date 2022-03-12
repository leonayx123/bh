package com.thoughtworks.bh.application.order.facotry;

import com.thoughtworks.bh.application.order.command.CreateEntrustRequestCmd;
import com.thoughtworks.bh.domain.entrust.model.EntrustRfp;
import com.thoughtworks.bh.domain.entrust.model.GoodsType;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @Description
 * @Author yangxun xun.yang@thoughtworks.com
 * @Version V1.0.0
 * @Since 1.0
 * @Date 2021/6/14
 */
@Mapper
public abstract class EntrustFactory {
    public static final EntrustFactory FACTORY = Mappers.getMapper(EntrustFactory.class);

    public abstract EntrustRfp build(CreateEntrustRequestCmd cmd);

    public GoodsType covertGoodsType(String goodType) {
        return GoodsType.byCode(goodType);
    }
}
