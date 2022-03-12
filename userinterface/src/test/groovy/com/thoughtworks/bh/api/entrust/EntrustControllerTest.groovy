package com.thoughtworks.bh.api.entrust

import com.thoughtworks.bh.api.entrust.request.CreateEntrustRfpRequest
import com.thoughtworks.bh.application.order.service.EntrustService
import com.thoughtworks.bh.domain.entrust.model.Entrust
import com.thoughtworks.bh.domain.entrust.model.EntrustRfp
import com.thoughtworks.bh.domain.entrust.model.EntrustStatus
import com.thoughtworks.bh.domain.entrust.model.GoodsType
import spock.lang.Specification

/**
 * @Description
 * @Author  yangxun xun.yang@thoughtworks.com
 * @Version V1.0.0* @Since 1.0* @Date 2021/6/14
 */
class EntrustControllerTest extends Specification {
    EntrustService entrustService = Mock()
    EntrustController controller = new EntrustController(entrustService)

    def "CreateEntrustRfp test"() {
        given:
            CreateEntrustRfpRequest request = CreateEntrustRfpRequest
                    .builder()
                    .name("古董花瓶")
                    .time("1840年")
                    .type("古董")
                    .requirePrice(new BigDecimal("200000"))
                    .userName("杨勋")
                    .phone("15691801558")
                    .build()

        when:
            def response = controller.createEntrustRfp(request)
        then:
            1 * entrustService.createRfp(_) >> true

            response.success == true
            response.message == "意向登记成功"
    }

    def "Cancel test"() {
        given:
            String id = "test1"

        when:
            def response = controller.cancel(id)

        then:
            response.success == true
            response.message == "撤销成功"

    }


    def "get Entrust test"() {
        given:
            String id = "test1"
        when:
            def response = controller.getEntrust(id)
        then:
            1 * entrustService.getById("test1") >> {
                return Entrust.builder()
                        .status(EntrustStatus.UN_AUCTION)
                        .entrustRfp(buildRfp()).build()

            }
            response.success == true
            response.result.status == "UN_AUCTION"
            response.result.entrustRfp.time == "1840年"
            response.result.entrustRfp.type == "ANTIQUE"
            response.result.entrustRfp.requirePrice == 200000
            response.result.entrustRfp.userName == "杨勋"
            response.result.entrustRfp.phone == "15691801558"
    }


    static EntrustRfp buildRfp() {
        return EntrustRfp.builder()
                .name("古董花瓶")
                .time("1840年")
                .type(GoodsType.ANTIQUE)
                .requirePrice(new BigDecimal("200000"))
                .userName("杨勋")
                .phone("15691801558").build()
    }

}
