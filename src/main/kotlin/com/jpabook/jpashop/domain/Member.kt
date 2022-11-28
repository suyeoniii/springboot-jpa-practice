package com.jpabook.jpashop.domain

import javax.persistence.*

@Entity
class Member(name: String) {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    var id: Long? = null

    var name: String = name

    @Embedded
    var address: Address? = null

    @OneToMany(mappedBy = "member")
    var orders: MutableList<Order> = ArrayList()
}
