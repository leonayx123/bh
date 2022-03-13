package com.thoughtworks.bh.infrastructure.repository.jpa;

import com.thoughtworks.bh.infrastructure.repository.dataobject.TicketDO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepo extends JpaRepository<TicketDO, Long> {
}
