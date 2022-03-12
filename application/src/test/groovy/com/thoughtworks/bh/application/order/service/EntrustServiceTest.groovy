package com.thoughtworks.bh.application.order.service

import com.thoughtworks.bh.application.order.command.CreateEntrustRequestCmd
import com.thoughtworks.bh.domain.common.BaseException
import com.thoughtworks.bh.domain.entrust.model.Entrust
import com.thoughtworks.bh.domain.entrust.model.EntrustRfp
import com.thoughtworks.bh.domain.entrust.model.EntrustStatus
import com.thoughtworks.bh.domain.entrust.model.GoodsType
import com.thoughtworks.bh.domain.entrust.repository.EntrustRepository
import spock.lang.Specification
import spock.lang.Unroll

/**
 * @Description
 * @Author  yangxun xun.yang@thoughtworks.com
 * @Version V1.0.0* @Since 1.0* @Date 2021/6/15
 */
class EntrustServiceTest extends Specification {
    EntrustRepository entrustRepository = Mock()
    EntrustService entrustService = new EntrustService(entrustRepository)

    def "createRfp success test"() {
        given:
            CreateEntrustRequestCmd cmd = CreateEntrustRequestCmd.builder()
                    .name("古董花瓶")
                    .time("1840年")
                    .type("古董")
                    .requirePrice(new BigDecimal("200000"))
                    .userName("杨勋")
                    .phone("15691801558")
                    .build()
        when:
            boolean result = entrustService.createRfp(cmd)
        then:
            1 * entrustRepository.createRfp({
                verifyAll(it, EntrustRfp) {
                    it.name == cmd.name
                    it.time == cmd.time
                    it.type == GoodsType.ANTIQUE
                    it.requirePrice == cmd.requirePrice
                    it.userName == cmd.userName
                    it.phone == cmd.phone
                }
                it
            } as EntrustRfp) >> true

            result == true
    }

    def "createRfp fail when give a wrong type test"() {
        given:
            CreateEntrustRequestCmd cmd = CreateEntrustRequestCmd.builder()
                    .name("古董花瓶")
                    .time("1840年")
                    .type("食物")
                    .requirePrice(new BigDecimal("200000"))
                    .userName("杨勋")
                    .phone("15691801558")
                    .build()
        when:
            entrustService.createRfp(cmd)
        then:
            def ex = thrown(BaseException)
            ex.message == "不支持的拍品类型"
            ex.code == 400
    }

    def "cancel success test"() {
        given:
            String id = "test1"
        when:
            boolean result = entrustService.cancel(id)
        then:
            1 * entrustRepository.getById("test1") >> new Entrust(id: "test1", status: EntrustStatus.UN_AUCTION)

            1 * entrustRepository.save({
                verifyAll(it, Entrust) {
                    it.id == "test1"
                    it.status == EntrustStatus.CANCELED
                }
                it
            } as Entrust)

            result == true

    }

    def "cancel fail when entrust not exists test"() {
        given:
            String id = "test1"
        when:
            entrustService.cancel(id)
        then:
            1 * entrustRepository.getById("test1") >> {
                throw new BaseException(404, "委托不存在")
            }

            def ex = thrown(BaseException)
            ex.message == "委托不存在"
            ex.code == 404

    }

    @Unroll
    def "cancel fail when entrust status not equals UN_AUCTION test"() {
        given:
            String id = "test1"
        when:
            entrustService.cancel(id)
        then:
            1 * entrustRepository.getById("test1") >> {
                return new Entrust(id: "test1", status: initStatus)
            }

            def ex = thrown(BaseException)
            ex.message == "状态已经无法撤销"
            ex.code == 500

        where:
            initStatus << [EntrustStatus.CANCELED, EntrustStatus.AUCTIONING, EntrustStatus.AUCTION_DEAL]
    }
}
