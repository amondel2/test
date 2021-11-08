package com.test

class Product {

    Integer id
    String name

    static constraints = {
        name blank: false, unique: true
    }

    static mapping = {
        id generator:'identity', name:'id'
    }
}