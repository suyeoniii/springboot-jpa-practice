package com.jpabook.jpashop.service

import com.jpabook.jpashop.domain.*
import com.jpabook.jpashop.repository.ItemRepository
import com.jpabook.jpashop.repository.MemberRepository
import com.jpabook.jpashop.repository.OrderRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
@Transactional(readOnly = true)
class OrderService(
    private val orderRepository: OrderRepository,
    private val memberRepository: MemberRepository,
    private val itemRepository: ItemRepository
) {
    /**
     * 주문
     */
    @Transactional
    fun order(memberId: Long, itemId: Long, count: Int): Long? {
        // 엔티티 조회
        val member = memberRepository.findOneById(memberId)
        val item = itemRepository.findOneById(itemId)

        // 배송정보 생성
        val delivery = Delivery(member!!.address!!, null, DeliveryStatus.READY) // TODO: !! 제거

        // 주문 상품 생성
        val orderItem =
            OrderItem().createOrderItem(item!!, item.price, count) // TODO: simplify

        // 주문 생성
        val order = Order(LocalDateTime.now(), OrderStatus.ORDER).createOrder(member, delivery, orderItem)

        // 주문 저장
        orderRepository.save(order)

        return order.id
    }

    /**
     * 주문 취소
     */
    @Transactional
    fun cancelOrder(orderId: Long) {
        val order = orderRepository.findOneById(orderId)
        // TODO: order가 없는 경우 처리
        order!!.cancel()
    }

    /**
     * 주문 검색
     */
}