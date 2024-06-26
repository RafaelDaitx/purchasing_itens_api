package com.rafaeldaitx.purchasingItens.repository

import com.rafaeldaitx.purchasingItens.model.Item
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ItemRepository: JpaRepository<Item, Long?> {
}