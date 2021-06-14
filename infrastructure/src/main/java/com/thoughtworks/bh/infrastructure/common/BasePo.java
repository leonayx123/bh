package com.thoughtworks.bh.infrastructure.common;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
@Getter
@Setter
public abstract class BasePo {
    @Id
    @GeneratedValue(generator = "jpa-uuid")
    private String id;
}
