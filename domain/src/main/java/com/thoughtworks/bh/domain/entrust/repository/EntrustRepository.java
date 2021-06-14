package com.thoughtworks.bh.domain.entrust.repository;

import com.thoughtworks.bh.domain.entrust.model.Entrust;
import com.thoughtworks.bh.domain.entrust.model.EntrustRfp;

/**
 * @Description
 * @Author yangxun xun.yang@thoughtworks.com
 * @Version V1.0.0
 * @Since 1.0
 * @Date 2021/6/14
 */
public interface EntrustRepository {
    Entrust getById(String id);

    boolean createRfp(EntrustRfp rfp);

    void save(Entrust entrust);


}
