package com.rafaeldaitx.purchasingItens.repository

import com.rafaeldaitx.purchasingItens.model.Item
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface ItemRepository: JpaRepository<Item, Long?> {

    @Query("SELECT i FROM Item i WHERE LOWER(i.itemName) LIKE LOWER(CONCAT('%', :itemName, '%'))")
    fun FindItemByName(@Param("itemName") itemName: String, pageable: Pageable): Page<Item>
}