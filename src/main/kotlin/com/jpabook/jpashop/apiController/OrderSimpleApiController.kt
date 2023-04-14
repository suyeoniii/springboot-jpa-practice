package com.jpabook.jpashop.apiController

import com.jpabook.jpashop.domain.Address
import com.jpabook.jpashop.domain.Order
import com.jpabook.jpashop.domain.OrderStatus
import com.jpabook.jpashop.repository.OrderRepository
import com.jpabook.jpashop.repository.OrderSearch
import com.jpabook.jpashop.repository.OrderSimpleQueryDto
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime
import java.util.stream.Collectors

/**
 * Order
 * Order -> Member
 * Order -> Delivery
 */
@RestController
class OrderSimpleApiController(private val orderRepository: OrderRepository) {
    @GetMapping("/api/v1/simple-orders")
    fun orderV1(): List<Order> {
        val all: List<Order> = orderRepository.findAllByString(OrderSearch())
        return all
    }

    @GetMapping("/api/v2/simple-orders")
    fun orderV2(): List<SimpleOrderDto> {
        val orders: List<Order> = orderRepository.findAllByString(OrderSearch())
        val result =
            orders.stream()
                .map { o -> SimpleOrderDto(o.id!!, o.member!!.name, o.orderDate, o.status, o.delivery!!.address) }
                .collect(Collectors.toList())
        return result
    }

    @GetMapping("/api/v3/simple-orders")
    fun orderV3(): List<SimpleOrderDto> {
        val orders: List<Order> = orderRepository.findAllWithMemberDelivery()
        val result =
            orders.stream()
                .map { o -> SimpleOrderDto(o.id!!, o.member!!.name, o.orderDate, o.status, o.delivery!!.address) }
                .collect(Collectors.toList())
        return result
    }

    @GetMapping("/api/v4/simple-orders")
    fun orderV4(): List<OrderSimpleQueryDto> {
        val result: List<OrderSimpleQueryDto> = orderRepository.findOrderDtos()
        return result
    }

    data class SimpleOrderDto(
        val orderId: Long,
        val name: String,
        val orderDate: LocalDateTime,
        val orderStatus: OrderStatus,
        val address: Address
    )
}