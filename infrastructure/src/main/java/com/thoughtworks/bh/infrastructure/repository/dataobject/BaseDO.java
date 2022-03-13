package com.thoughtworks.bh.infrastructure.repository.dataobject;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseDO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;
}
