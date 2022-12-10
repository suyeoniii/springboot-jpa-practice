package com.jpabook.jpashop.repository

import com.jpabook.jpashop.domain.Order
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface OrderRepository : JpaRepository<Order, Long> {
    fun findOneById(orderId: Long): Order?
}