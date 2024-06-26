package com.rafaeldaitx.purchasingItens.data.vo.v1

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonPropertyOrder
import com.github.dozermapper.core.Mapping


@JsonPropertyOrder("id", "itemName", "marketplace", "link", "price")
data class ItemsVO (

    @Mapping("id")
    var key: Long = 0,
    var itemName: String = "",
    var marketplace: String = "",
    var link: String = "",
    var price: Double = 0.0
)