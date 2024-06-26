package com.rafaeldaitx.purchasingItens.model

import jakarta.persistence.*

@Entity
@Table(name = "items")
data class Item(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    @Column(name = "item_name", nullable = false, length = 100)
    var itemName: String = "",

    @Column(name = "marketplace", nullable = false, length = 50)
    var marketplace: String = "",

    @Column(name = "link", nullable = false, length = 500)
    var link: String = "",

    @Column(nullable = false)
    var price: Double = 0.0
)