package com.jpabook.jpashop.domain

import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "orders")
class Order(orderDate: LocalDateTime, status: OrderStatus) {

    @Id
    @GeneratedValue
    @Column(name = "order_id")
    var id: Long? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    var member: Member? = null

    @OneToMany(mappedBy = "order", cascade = [CascadeType.ALL])
    var orderItems: MutableList<OrderItem> = ArrayList()

    @OneToOne(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery_id")
    var delivery: Delivery? = null

    var orderDate: LocalDateTime = orderDate

    @Enumerated(EnumType.STRING)
    var status: OrderStatus = status // Order, Cancel

    //== 연관관계 편의 메서드 ==//
    fun setMember(member: Member): Int {
        this.member = member
        member.orders.add(this)
        return 1
    }

    fun setDelivery(delivery: Delivery): Int {
        this.delivery = delivery
        delivery.order = this
        return 1
    }

    fun setOrderItem(orderItem: OrderItem): Int {
        orderItems.add(orderItem)
        orderItem.order = this
        return 1
    }

    //== 생성 메서드 ==//
    fun createOrder(member: Member, delivery: Delivery, vararg orderItems: OrderItem): Order {
        // order 생성
        var order = Order(LocalDateTime.now(), OrderStatus.ORDER)
        order.setMember(member)
        order.setDelivery(delivery)

        for (orderItem in orderItems) {
            order.setOrderItem(orderItem)
        }

        return order
    }

    //== 비즈니스 로직 ==//
    /**
     * 주문 취소
     */
    fun cancel() {
        if (delivery !== null && delivery!!.status === DeliveryStatus.COMP) {
            throw IllegalStateException("이미 배송 완료된 상품은 취소가 불가능합니다.")
        }

        this.status = OrderStatus.CANCELD
        for (orderItem in orderItems) {
            orderItem.cancel()
        }
    }

    //== 조회 로직 ==//
    /**
     * 전체 주문 가격 조회
     */
    fun getTotalPrice(): Int {
        return orderItems
            .stream()
            .mapToInt(OrderItem::getTotalPrice)
            .sum()
    }

}
