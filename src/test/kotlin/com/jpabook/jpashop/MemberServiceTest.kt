package com.jpabook.jpashop

import com.jpabook.jpashop.member.domain.Member
import com.jpabook.jpashop.member.repository.MemberRepository
import com.jpabook.jpashop.member.service.MemberService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional

@SpringBootTest
@Transactional
// @Rollback(false)
class MemberServiceTest {

    @Autowired
    private lateinit var memberService: MemberService

    @Autowired
    private lateinit var memberRepository: MemberRepository

    @Test
    fun 회원가입() {
        // given
        val member = Member("kim")

        // when
        val savedId = memberService.join(member)

        // then
        assertEquals(member, memberRepository.findOneById(savedId ?: -1))
    }

    @Test
    fun 중복회원예외() {
        // given
        val member1 = Member("kim")
        val member2 = Member("kim")

        // when
        memberService.join(member1)

        // then
        assertThrows(IllegalStateException::class.java) { memberService.join(member2) }
    }
}
