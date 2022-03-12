package com.thoughtworks.bh.api.mapper;

import com.thoughtworks.bh.api.entrust.reponse.GetEntrustResponse;
import com.thoughtworks.bh.api.entrust.request.CreateEntrustRfpRequest;
import com.thoughtworks.bh.application.order.command.CreateEntrustRequestCmd;
import com.thoughtworks.bh.domain.entrust.model.Entrust;
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
public abstract class ApiEntrustMapper {
    public static final ApiEntrustMapper MAPPER = Mappers.getMapper(ApiEntrustMapper.class);

    public abstract CreateEntrustRequestCmd requestToCommand(CreateEntrustRfpRequest request);

    public abstract GetEntrustResponse modelToResponse(Entrust entrust);

}
