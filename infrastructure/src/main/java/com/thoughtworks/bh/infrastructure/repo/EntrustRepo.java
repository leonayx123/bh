package com.thoughtworks.bh.infrastructure.repo;


import com.thoughtworks.bh.infrastructure.entity.EntrustEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Description
 * @Author yangxun xun.yang@thoughtworks.com
 * @Version V1.0.0
 * @Since 1.0
 * @Date 2021/6/14
 */
@Repository
public interface EntrustRepo extends JpaRepository<EntrustEntity, String> {
}
