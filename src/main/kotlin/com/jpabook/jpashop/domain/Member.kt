package com.jpabook.jpashop.domain

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*

@Entity
class Member(name: String, address: Address?) {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    var id: Long? = null
    
    var name: String = name

    @Embedded
    var address: Address? = address

    @OneToMany(mappedBy = "member")
    @JsonIgnore
    var orders: MutableList<Order> = ArrayList()
}
