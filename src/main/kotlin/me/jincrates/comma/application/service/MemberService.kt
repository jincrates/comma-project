package me.jincrates.comma.application.service

import me.jincrates.comma.application.port.MemberPort
import me.jincrates.comma.domain.entity.Member
import me.jincrates.comma.domain.exception.MemberDomainException
import me.jincrates.comma.domain.usecase.MemberDomainUseCase
import org.springframework.stereotype.Service

@Service
class MemberService(
        private val memberPort: MemberPort
) : MemberDomainUseCase {

    /**
     * 회원 가입
     */
    override fun register(member: Member): Member {
        // let 함수는 findByEmail()의 결과가 null이 아닐 경우에만 블로 내의 코드를 실행합니다.
        memberPort.findByEmail(member.email)?.let {
            throw MemberDomainException("이미 가입된 이메일입니다.");
        }

        return memberPort.saveMember(member)
    }
}