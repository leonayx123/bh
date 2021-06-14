package com.thoughtworks.bh.application.order.service;


import com.thoughtworks.bh.application.order.command.CreateEntrustRequestCmd;
import com.thoughtworks.bh.application.order.facotry.EntrustFactory;
import com.thoughtworks.bh.domain.entrust.model.Entrust;
import com.thoughtworks.bh.domain.entrust.model.EntrustRfp;
import com.thoughtworks.bh.domain.entrust.repository.EntrustRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EntrustService {
    private final EntrustRepository entrustRepository;

    public boolean createRfp(CreateEntrustRequestCmd cmd) {
        EntrustRfp rfp = EntrustFactory.FACTORY.build(cmd);
        return entrustRepository.createRfp(rfp);
    }

    public boolean cancel(String id) {
        Entrust entrust = entrustRepository.getById(id);
        entrust.cancel();
        entrustRepository.save(entrust);
        return true;
    }

    public Entrust getById(String id) {
        return entrustRepository.getById(id);
    }
}
