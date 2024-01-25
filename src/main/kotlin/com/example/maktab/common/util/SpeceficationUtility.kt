package com.example.maktab.common.util

import org.springframework.data.jpa.domain.Specification

operator fun <T> Specification<T>.plus(that: Specification<T>): Specification<T> {
    return this.or(that)
}

operator fun <T> Specification<T>.times(that: Specification<T>): Specification<T> {
    return this.and(that)
}