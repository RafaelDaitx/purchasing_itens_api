package com.rafaeldaitx.purchasingItens.data.vo.v1

import com.fasterxml.jackson.annotation.JsonPropertyOrder
import com.github.dozermapper.core.Mapping


@JsonPropertyOrder("id", "categoryName")
data class CategoriesVO (

    @Mapping("id")
    var key: Long = 0,
    var categoryName: String = ""
)