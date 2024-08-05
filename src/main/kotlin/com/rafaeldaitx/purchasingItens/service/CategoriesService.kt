package com.rafaeldaitx.purchasingItens.service

import com.rafaeldaitx.purchasingItens.data.vo.v1.CategoriesVO
import com.rafaeldaitx.purchasingItens.exceptions.RequiredObjectsNullException
import com.rafaeldaitx.purchasingItens.exceptions.ResourceNotFoundException
import com.rafaeldaitx.purchasingItens.model.DozerMapper
import com.rafaeldaitx.purchasingItens.model.categories.Categories
import com.rafaeldaitx.purchasingItens.repository.CategoriesRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.logging.Logger

@Service
class CategoriesService {

    @Autowired
    private lateinit var repository: CategoriesRepository

    private val logger = Logger.getLogger(CategoriesService::class.java.name)
    fun create(category: CategoriesVO): CategoriesVO{
        logger.info("Creating new category!")
        var entity: Categories = DozerMapper.parseObject(category, Categories::class.java)

        if(entity.categoryName.isNullOrBlank()) throw RequiredObjectsNullException()

        return DozerMapper.parseObject(repository.save(entity), CategoriesVO::class.java)
    }

    fun findAll(): List<CategoriesVO>{
        logger.info("Finding all categories!")

        val categorias = repository.findAll()
        return DozerMapper.parseListObject(categorias, CategoriesVO::class.java)
    }

    fun findById(id: Long): CategoriesVO{
        logger.info("Finding a category with ID ${id}!")

        val category = repository.findById(id).orElseThrow(){
            ResourceNotFoundException("No records found for this ID!")
        }

        return DozerMapper.parseObject(category, CategoriesVO::class.java)
    }

    fun delete(id: Long){
        logger.info("Deleting category with ID ${id}")

        val entity = repository.findById(id).orElseThrow(){
            ResourceNotFoundException("No records found for this ID!")
        }

        repository.delete(entity)
    }
}