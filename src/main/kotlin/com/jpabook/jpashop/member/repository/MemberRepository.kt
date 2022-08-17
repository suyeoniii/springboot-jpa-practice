package com.jpabook.jpashop.member.repository

import com.jpabook.jpashop.member.domain.Member
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MemberRepository: JpaRepository<Member, Long> {
    fun save(member: Member): Long?
    fun find(id: Long): Member?
    fun findByUsername(username: String): List<Member?>
}