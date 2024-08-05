package com.rafaeldaitx.purchasingItens.repository

import com.rafaeldaitx.purchasingItens.model.categories.Categories
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CategoriesRepository: JpaRepository<Categories, Long?> {}