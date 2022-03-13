package com.thoughtworks.bh

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import spock.lang.Specification

@DataJpaTest
class JpaSpecification extends Specification {

    @Autowired
    TestEntityManager entityManager;
}
