package com.rafaeldaitx.purchasingItens.data.vo.v1

import com.fasterxml.jackson.annotation.JsonProperty
import com.github.dozermapper.core.Mapping


data class ItemsVO (

    var id: Long = 0,
    var itenName: String = "",
    var marketplace: String = "",
    var link: String = "",
    var price: Double = 0.0
)