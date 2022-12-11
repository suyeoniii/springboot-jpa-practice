package com.jpabook.jpashop.apiController

import com.jpabook.jpashop.domain.Member
import com.jpabook.jpashop.service.MemberService
import org.springframework.web.bind.annotation.*
import java.util.stream.Collectors
import javax.validation.Valid

@RestController
class MemberApiController(private val memberService: MemberService) {
    // V1 save member
    @PostMapping("/api/v2/members")
    fun saveMemberV2(@RequestBody @Valid request: CreateMemberRequest): CreateMemberResponse {
        val member = Member(request.name, null)
        val id = memberService.join(member)
        return CreateMemberResponse(id)
    }

    @PutMapping("/api/v2/members/{id}")
    fun updateMemberV2(
        @PathVariable("id") id: Long,
        @RequestBody @Valid request: UpdateMemberRequest
    ): UpdateMemberResponse {
        memberService.update(id, request.name)
        val member = memberService.findOne(id)
        return UpdateMemberResponse(member!!.id!!, member.name)
    }

    @GetMapping("/api/v2/members")
    fun memberV2(): Result<List<MemberDto>> {
        val findMembers = memberService.findMembers()
        val collect = findMembers.stream()
            .map { m -> MemberDto(m.name) }.collect(Collectors.toList<MemberDto>())
        return Result(collect)
    }

    data class Result<T>(
        val data: T
    )

    data class MemberDto(
        val name: String
    )

    data class UpdateMemberRequest(
        val name: String
    )

    data class UpdateMemberResponse(
        val id: Long,
        val name: String
    )

    data class CreateMemberRequest(
        val name: String
    )

    data class CreateMemberResponse(
        val id: Long
    )
}