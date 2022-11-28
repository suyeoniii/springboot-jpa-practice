package com.jpabook.jpashop.domain

import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "orders")
class Order(orderDate: LocalDateTime, member: Member, status: OrderStatus) {

    @Id
    @GeneratedValue
    @Column(name = "order_id")
    var id: Long? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    var member: Member = member

    @OneToMany(mappedBy = "order")
    var orderItems: MutableList<OrderItem> = ArrayList()

    @OneToOne
    @JoinColumn(name = "delivery_id")
    var delivery: Delivery? = null

    var orderDate: LocalDateTime = orderDate

    @Enumerated(EnumType.STRING)
    var status: OrderStatus = status // Order, Cancel
}
