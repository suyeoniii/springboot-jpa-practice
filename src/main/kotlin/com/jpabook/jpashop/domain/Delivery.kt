package com.jpabook.jpashop.domain

import javax.persistence.*

@Entity
class Delivery(address: Address, order: Order?, status: DeliveryStatus) {
    @Id
    @GeneratedValue
    @Column(name = "delivery_id")
    var id: Long? = null

    @OneToOne(mappedBy = "delivery")
    var order: Order? = order

    @Embedded
    var address: Address = address

    @Enumerated(EnumType.STRING)
    var status: DeliveryStatus = status
}
