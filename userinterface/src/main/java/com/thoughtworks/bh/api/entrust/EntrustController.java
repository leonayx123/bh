package com.thoughtworks.bh.api.entrust;

import com.thoughtworks.bh.api.common.CommonResponse;
import com.thoughtworks.bh.api.entrust.reponse.GetEntrustResponse;
import com.thoughtworks.bh.api.entrust.request.CreateEntrustRfpRequest;
import com.thoughtworks.bh.api.mapper.ApiEntrustMapper;
import com.thoughtworks.bh.application.order.command.CreateEntrustRequestCmd;
import com.thoughtworks.bh.application.order.service.EntrustService;
import com.thoughtworks.bh.domain.entrust.model.Entrust;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 把接口的实现类,发布给其他微服务使用
 */
@RestController
@RequiredArgsConstructor
public class EntrustController {
    private final EntrustService entrustService;


    @PostMapping("/entrust-requests")
    public CommonResponse<Void> createEntrustRfp(CreateEntrustRfpRequest request) {
        CreateEntrustRequestCmd cmd = ApiEntrustMapper.MAPPER.requestToCommand(request);
        entrustService.createRfp(cmd);
        return CommonResponse.success("意向登记成功");
    }

    @PostMapping("/entrust/{id}/cancel")
    public CommonResponse<Void> cancel(@PathVariable("id") String id) {
        boolean result = entrustService.cancel(id);
        return CommonResponse.success("撤销成功");
    }

    @GetMapping("/entrust/{id}")
    public CommonResponse<GetEntrustResponse> getEntrust(@PathVariable("id") String id) {
        Entrust entrust = entrustService.getById(id);
        GetEntrustResponse response = ApiEntrustMapper.MAPPER.modelToResponse(entrust);
        return CommonResponse.success("撤销成功", response);
    }
}
