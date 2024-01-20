package com.example.maktab.common.specification

import jakarta.persistence.criteria.CriteriaBuilder
import jakarta.persistence.criteria.CriteriaQuery
import jakarta.persistence.criteria.Predicate
import jakarta.persistence.criteria.Root
import org.springframework.data.jpa.domain.Specification

interface BaseSpecification<T> : Specification<T> {
    override fun toPredicate(
        root: Root<T>,
        query: CriteriaQuery<*>,
        criteriaBuilder: CriteriaBuilder
    ): Predicate? {
        return build().toPredicate(root, query, criteriaBuilder)
    }

    fun build(): Specification<T>
}