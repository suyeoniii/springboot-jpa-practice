package com.jpabook.jpashop.repository

import com.jpabook.jpashop.domain.Address
import com.jpabook.jpashop.domain.OrderStatus
import java.time.LocalDateTime

data class OrderSimpleQueryDto(
    val orderId: Long,
    val name: String,
    val orderDate: LocalDateTime,
    val orderStatus: OrderStatus,
    val address: Address
)