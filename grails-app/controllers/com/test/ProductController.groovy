package com.test

import grails.converters.JSON
import org.hibernate.NonUniqueResultException

class ProductController {

    ProductService productService

    def singleItem() {
        def rtn
        try {
            rtn = productService.singleItem({ like('name', '%Test') }  )
        } catch(NonUniqueResultException ex) {
            rtn = [ex : ex.getMessage(), type:ex.getClass().name]
        }
        withFormat{
            '*' {
                render(rtn as JSON)
            }
        }
    }

    def singleItemGood() {
        def rtn
        try {
            rtn = productService.singleItem({ like('id', 1) }  )
        } catch(NonUniqueResultException ex) {
            rtn = [ex : ex.getMessage()]
        }
        withFormat{
            '*' {
                render(rtn as JSON)
            }
        }
    }

    def list() {
        def rtn = productService.list()
        withFormat{
            '*' {
                render(rtn as JSON)
            }
        }
    }
}
