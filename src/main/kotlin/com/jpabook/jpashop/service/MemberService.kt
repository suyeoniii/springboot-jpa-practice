package com.jpabook.jpashop.service

import com.jpabook.jpashop.domain.Member
import com.jpabook.jpashop.repository.MemberRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class MemberService(private val memberRepository: MemberRepository) {
    /**
     * 회원 가입
     */
    @Transactional
    fun join(member: Member): Long {
        validateDuplicateMember(member)
        memberRepository.save(member)
        return member.id!!
    }

    fun validateDuplicateMember(member: Member) {
        val findMembers = memberRepository.findByName(member.name)
        if (findMembers.isNotEmpty()) {
            throw IllegalStateException("이미 존재하는 회원입니다")
        }
    }

    /**
     * 회원 전체 조회
     */
    fun findMembers(): List<Member> {
        return memberRepository.findAll()
    }

    /**
     * 회원 조회
     */
    fun findOne(memberId: Long): Member? {
        // TODO: member가 없는 경우 error
        return memberRepository.findOneById(memberId)
    }

    @Transactional
    fun update(id: Long, name: String) {
        val member = memberRepository.findOneById(id)
        // TODO: member가 없는 경우 error
        member!!.name = name // 변경 감지
    }
}
