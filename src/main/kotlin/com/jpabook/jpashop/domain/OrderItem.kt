package com.jpabook.jpashop.domain

import com.jpabook.jpashop.domain.item.Item
import javax.persistence.*

@Entity
class OrderItem(item: Item, order: Order, orderPrice: Int, count: Int) {
    @Id
    @GeneratedValue
    @Column(name = "order_item_id")
    var id: Long? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    var item: Item = item

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    var order: Order = order

    var orderPrice: Int = orderPrice // 주문 당시 가격

    var count: Int = count // 주문 수량
}
