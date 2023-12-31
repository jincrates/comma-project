package me.jincrates.comma.domain.valueobject

abstract class BaseId<T>(val value: T) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as BaseId<*>

        return value == other.value
    }

    override fun hashCode(): Int {
        return value?.hashCode() ?: 0
    }
}

class MemberId(
        value: Long
) : BaseId<Long>(value)

class MemberRoleId(
        value: Long
) : BaseId<Long>(value)