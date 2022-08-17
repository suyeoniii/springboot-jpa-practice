package com.jpabook.jpashop.member.domain

import javax.persistence.*

@Entity
class Member(username: String) {

    @Id
    @GeneratedValue
    val id: Long? = null

    @Column(length = 30, unique = true)
    val username: String = username
}