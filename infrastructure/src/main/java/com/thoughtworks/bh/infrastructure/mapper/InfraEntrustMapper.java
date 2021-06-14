package com.thoughtworks.bh.infrastructure.mapper;

import com.thoughtworks.bh.domain.entrust.model.Entrust;
import com.thoughtworks.bh.domain.entrust.model.EntrustRfp;
import com.thoughtworks.bh.domain.entrust.model.EntrustStatus;
import com.thoughtworks.bh.domain.entrust.model.GoodsType;
import com.thoughtworks.bh.infrastructure.entity.EntrustEntity;
import com.thoughtworks.bh.infrastructure.entity.EntrustRequestEntity;
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
public abstract class InfraEntrustMapper {
    public static final InfraEntrustMapper MAPPER = Mappers.getMapper(InfraEntrustMapper.class);

    public abstract EntrustRequestEntity modelToDataEntity(EntrustRfp rfp);

    public abstract EntrustRfp entityToModel(EntrustRequestEntity entrustRequestEntity);

    public abstract Entrust entityToEntrustModel(EntrustEntity entrustEntity);

    public abstract EntrustEntity entrustModelToEntity(Entrust entrust);

    public String convertGoodsType(GoodsType goodsType) {
        return goodsType.name();
    }

    public GoodsType convertGoodsType(String goodsType) {
        return GoodsType.valueOf(goodsType);
    }

}
