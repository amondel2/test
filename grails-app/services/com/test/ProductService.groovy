package com.test

import grails.gorm.transactions.Transactional

@Transactional
class ProductService {

    def list() {
        Product.createCriteria().list{}
    }

    def singleItem(Closure parms) {
        Product.createCriteria().get(parms)
    }
}
