package com.jpabook.jpashop.member.repository

import com.jpabook.jpashop.member.domain.Member
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface MemberRepository: JpaRepository<Member, Long> {
    fun save(member: Member): Member?
    fun findOneById(id: Long): Member?
    fun findByUsername(username: String): List<Member?>
}