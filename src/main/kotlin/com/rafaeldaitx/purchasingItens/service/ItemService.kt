package com.rafaeldaitx.purchasingItens.service

import com.rafaeldaitx.purchasingItens.data.vo.v1.ItemsVO
import com.rafaeldaitx.purchasingItens.exceptions.ResourceNotFoundException
import com.rafaeldaitx.purchasingItens.model.DozerMapper
import com.rafaeldaitx.purchasingItens.model.Item
import com.rafaeldaitx.purchasingItens.repository.ItemRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.logging.Logger

@Service
class ItemService {

    @Autowired
    private lateinit var repository: ItemRepository

    private val logger = Logger.getLogger(ItemService::class.java.name)

    fun findAll(): List<ItemsVO> {
        logger.info("Finding all items!")

        val item = repository.findAll()
        return DozerMapper.parseListObject(item, ItemsVO::class.java)
    }


    fun findById(id: Long): ItemsVO {
        logger.info("Finding a item with ID ${id}!")

        val item = repository.findById(id).orElseThrow{ ResourceNotFoundException("No records found for this ID!") }
        return DozerMapper.parseObject(item, ItemsVO::class.java)
    }
}