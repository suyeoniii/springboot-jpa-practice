package com.jpabook.jpashop.member.domain

import javax.persistence.*

@Entity
class Member(username: String) {

    @Id
    @GeneratedValue
    var id: Long? = null

    @Column(length = 30, unique = true)
    var username: String = username
}