package com.thoughtworks.bh.infrastructure.repository

import com.thoughtworks.bh.domain.entrust.model.Entrust
import com.thoughtworks.bh.domain.entrust.model.EntrustRfp
import com.thoughtworks.bh.domain.entrust.model.GoodsType
import com.thoughtworks.bh.domain.entrust.repository.EntrustRepository
import com.thoughtworks.bh.infrastructure.JpaSpecification
import com.thoughtworks.bh.infrastructure.entity.EntrustEntity
import com.thoughtworks.bh.infrastructure.entity.EntrustRequestEntity
import com.thoughtworks.bh.infrastructure.jpaclient.EntrustRepo
import com.thoughtworks.bh.infrastructure.jpaclient.EntrustRequestRepo

/**
 * @Description
 * @Author  yangxun xun.yang@thoughtworks.com
 * @Version V1.0.0* @Since 1.0* @Date 2021/6/15
 */
class EntrustRepositoryImplTest extends JpaSpecification {

    EntrustRepo entrustRepo = Mock()
    EntrustRequestRepo entrustRequestRepo = Mock()
    def EntrustRepository entrustRepository

    def setup() {
        entrustRepository = new EntrustRepositoryImpl(entrustRepo, entrustRequestRepo)
    }

    def "createRfp"() {
        given:
            EntrustRfp rfp = EntrustRfp.builder()
                    .name("古董花瓶")
                    .time("1840年")
                    .type(GoodsType.ANTIQUE)
                    .requirePrice(new BigDecimal("200000"))
                    .userName("杨勋")
                    .phone("15691801558").build();
        when:
            entrustRepository.createRfp(rfp)

        then:
            1 * entrustRequestRepo.save({
                verifyAll(it, EntrustRequestEntity) {
                    it.name == "古董花瓶"
                    it.time == "1840年"
                    it.type == GoodsType.ANTIQUE.name()
                    it.requirePrice == new BigDecimal("200000")
                    it.userName == "杨勋"
                    it.phone == "15691801558"
                }
                it
            } as EntrustRequestEntity)

    }

    def "GetbyId test"() {
        when:
            entrustRepository.getById("id")
        then:
            1 * entrustRepo.findById("id") >> Optional.of(new EntrustEntity(id: "id"))
    }

    def "Save"() {
        given:
        when:
            entrustRepository.save("id")
        then:
            1 * entrustRepo.findById("id") >> Optional.of(new EntrustEntity(id: "id"))
    }
}
