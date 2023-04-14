package com.jpabook.jpashop

import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class JpashopApplication

fun main(args: Array<String>) {
    runApplication<JpashopApplication>(*args)

    @Bean
    fun hibernate5Module(): Hibernate5Module {
        return Hibernate5Module()
    }
}
