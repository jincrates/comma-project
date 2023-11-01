package me.jincrates.comma.domain.entity.member

import me.jincrates.comma.domain.entity.AggregateRoot
import me.jincrates.comma.domain.valueobject.MemberId

class Member(memberId: MemberId, val email: String? = null, val name: String? = null) : AggregateRoot<MemberId>(memberId)