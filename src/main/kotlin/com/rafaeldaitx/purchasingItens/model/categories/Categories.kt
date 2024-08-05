package com.rafaeldaitx.purchasingItens.model.categories

import com.rafaeldaitx.purchasingItens.model.Item
import jakarta.persistence.*
@Entity
@Table(name = "categories")
data class Categories (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    @Column(name = "category_name", nullable = false, length = 100)
    var categoryName: String = ""
)