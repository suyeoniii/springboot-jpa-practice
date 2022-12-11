package com.jpabook.jpashop.repository

import com.jpabook.jpashop.domain.Order
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface OrderRepository : JpaRepository<Order, Long> {
    fun findOneById(orderId: Long): Order?

    @Query("select o from Order o join o.member m")
    fun findAll(orderSearch: OrderSearch): List<Order>
}