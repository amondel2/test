package com.test

import grails.testing.gorm.DataTest
import grails.testing.services.ServiceUnitTest
import org.hibernate.NonUniqueResultException
import org.springframework.test.annotation.Rollback
import spock.lang.Specification

@Rollback
class ProductServiceSpec extends Specification implements DataTest,ServiceUnitTest<ProductService>{

    def setupSpec(){
        mockDomain Product
    }

    def setup() {
        new Product(id: 1, name:"Great Test").save()
        new Product(id: 2, name:"OK Test").save()
        new Product(id: 3, name:"Bad Test").save()
        new Product(id: 4, name:"No Test").save()
    }

    def cleanup() {
    }

    void "getList"() {
        when:
            def rs = service.list()
        then:
            rs.size() == 4
    }

    void "getSingle"() {
        when:
        def rs = service.singleItem({eq('id',1)})
        then:
        rs.id == 1
    }

    void "thisShouldPass"() {
        when:
        try {
            def rs = service.singleItem({ like('name', '%Test') }  )
        } catch ( NonUniqueResultException ex) {
            throw ex
        }
        then:
            NonUniqueResultException rex = thrown()
    }

    void "thisShouldFail"() {
        when:
        def rs
        try {
            rs = service.singleItem({ like('name', '%Test')
                order('id','asc') })
        } catch ( NonUniqueResultException ex) {
            throw ex
        }
        then:
         rs.id == 1
    }
}
