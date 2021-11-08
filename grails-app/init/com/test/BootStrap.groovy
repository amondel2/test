package com.test

class BootStrap {

    def init = { servletContext ->
        new Product(id: 1, name:"Great Test").save()
        new Product(id: 2, name:"OK Test").save()
    }
    def destroy = {
    }
}
