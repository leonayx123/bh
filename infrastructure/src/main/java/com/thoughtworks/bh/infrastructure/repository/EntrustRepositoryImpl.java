package com.thoughtworks.bh.infrastructure.repository;

import com.thoughtworks.bh.domain.common.BaseException;
import com.thoughtworks.bh.domain.entrust.model.Entrust;
import com.thoughtworks.bh.domain.entrust.model.EntrustRfp;
import com.thoughtworks.bh.domain.entrust.repository.EntrustRepository;
import com.thoughtworks.bh.infrastructure.entity.EntrustEntity;
import com.thoughtworks.bh.infrastructure.entity.EntrustRequestEntity;
import com.thoughtworks.bh.infrastructure.mapper.InfraEntrustMapper;
import com.thoughtworks.bh.infrastructure.repo.EntrustRepo;
import com.thoughtworks.bh.infrastructure.repo.EntrustRequestRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @Description
 * @Author yangxun xun.yang@thoughtworks.com
 * @Version V1.0.0
 * @Since 1.0
 * @Date 2021/6/14
 */
@Service
@RequiredArgsConstructor
public class EntrustRepositoryImpl implements EntrustRepository {
    private final EntrustRepo entrustRepo;
    private final EntrustRequestRepo entrustRequestRepo;

    @Override
    public boolean createRfp(EntrustRfp entrustRfp) {
        EntrustRequestEntity dataEntity = InfraEntrustMapper.MAPPER.modelToDataEntity(entrustRfp);
        entrustRequestRepo.save(dataEntity);
        return true;
    }

    @Override
    public Entrust getById(String id) {
        EntrustEntity entrustEntity = entrustRepo.findById(id).orElseThrow(() -> new BaseException(404, "委托不存在"));
        return InfraEntrustMapper.MAPPER.entityToEntrustModel(entrustEntity);
    }

    @Override
    public void save(Entrust entrust) {
        entrustRepo.save(InfraEntrustMapper.MAPPER.entrustModelToEntity(entrust));
    }
}
