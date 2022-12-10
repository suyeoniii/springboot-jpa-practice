package com.jpabook.jpashop

import com.jpabook.jpashop.domain.Address
import com.jpabook.jpashop.domain.Member
import com.jpabook.jpashop.repository.MemberRepository
import com.jpabook.jpashop.service.MemberService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import javax.transaction.Transactional

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
        val member = Member("kim", Address("서울", "화랑로", "123-123"))

        // when
        val savedId = memberService.join(member)

        // then
        assertEquals(member, memberRepository.findOneById(savedId ?: -1))
    }

    @Test
    fun 중복회원예외() {
        // given
        val member1 = Member("kim", Address("서울", "화랑로", "123-123"))
        val member2 = Member("kim", Address("서울", "화랑로", "123-123"))

        // when
        memberService.join(member1)

        // then
        assertThrows(IllegalStateException::class.java) { memberService.join(member2) }
    }
}
