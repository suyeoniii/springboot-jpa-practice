package com.jpabook.jpashop

import com.jpabook.jpashop.member.domain.Member
import com.jpabook.jpashop.member.repository.MemberRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.annotation.Rollback
import org.springframework.transaction.annotation.Transactional

@SpringBootTest
internal class MemberRepositoryTest(private val memberRepository: MemberRepository) {

    @Test
    @Transactional
    @Rollback(false)
    fun testMember() {
        // given
        val member = Member("memberA")

        // when
        val savedMember = memberRepository.save(member)
        val findMember = memberRepository.findOneById(savedMember?.id?:-1)

        // then
        assertEquals(findMember, member)
    }
}