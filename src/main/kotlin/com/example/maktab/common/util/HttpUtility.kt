package com.example.maktab.common.util

import com.example.maktab.common.dto.ApiDTO
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.ser.FilterProvider
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider


infix fun <T> ApiDTO.Response.Default<T>.applyFilter(jsonFilterName: String): ApiDTO.Response.Default<T> {
    val filters: FilterProvider = SimpleFilterProvider().addFilter(
        "myFilter",
        SimpleBeanPropertyFilter.filterOutAllExcept("name")
    )

    val result = ObjectMapper()
        .writer(filters)
        .writeValueAsString(this)
    
    return result as ApiDTO.Response.Default<T>
}