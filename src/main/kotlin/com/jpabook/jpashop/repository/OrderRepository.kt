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
    fun findAllByString(orderSearch: OrderSearch): List<Order>

    @Query("SELECT o FROM Order o JOIN FETCH o.member m JOIN FETCH o.delivery d")
    fun findAllWithMemberDelivery(): List<Order>

    @Query("SELECT new com.jpabook.jpashop.repository.OrderSimpleQueryDto(o.id, m.name, o.orderDate, o.status, d.address) FROM Order o JOIN o.member m JOIN o.delivery d")
    fun findOrderDtos(): List<OrderSimpleQueryDto>
}