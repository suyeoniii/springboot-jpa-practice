package com.jpabook.jpashop.repository

import com.jpabook.jpashop.domain.Member
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MemberRepository : JpaRepository<Member, Long> {
    fun save(member: Member): Member?
    fun findOneById(id: Long): Member?
    fun findByName(name: String): List<Member?>
}
