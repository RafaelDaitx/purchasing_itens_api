package com.rafaeldaitx.purchasingItens.service

import com.rafaeldaitx.purchasingItens.data.vo.v1.ItemsVO
import com.rafaeldaitx.purchasingItens.exceptions.ResourceNotFoundException
import com.rafaeldaitx.purchasingItens.model.DozerMapper
import com.rafaeldaitx.purchasingItens.model.Item
import com.rafaeldaitx.purchasingItens.model.PagedResponse
import com.rafaeldaitx.purchasingItens.repository.ItemRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PagedResourcesAssembler
import org.springframework.hateoas.EntityModel
import org.springframework.hateoas.PagedModel
import org.springframework.stereotype.Service
import java.util.logging.Logger

@Service
class ItemService {

    @Autowired
    private lateinit var repository: ItemRepository

    @Autowired
    private lateinit var assembler: PagedResourcesAssembler<ItemsVO>

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

    fun create(item: ItemsVO): ItemsVO{
        logger.info("Creating a new item!")

        var entity: Item = DozerMapper.parseObject(item, Item::class.java)

        return DozerMapper.parseObject(repository.save(entity),ItemsVO::class.java)
    }

    fun delete(id: Long){
        logger.info("Deleting item with ID ${id}")

        val entity = repository.findById(id).orElseThrow { ResourceNotFoundException("No records found for this ID!") }

        repository.delete(entity)
    }

    fun update(item: Item): Item{
        logger.info("Updating item with ID ${item.id}")
        val entity = repository.findById(item.id).orElseThrow() {ResourceNotFoundException("No records found" +
                "for this ID!")}

        entity.itemName = item.itemName
        entity.link = item.link
        entity.marketplace = item.marketplace
        entity.price = item.price

        return repository.save(entity)
    }

    fun findByName(itemName: String, pageable: Pageable): PagedResponse<ItemsVO> {
         logger.info("Finding an item by name")

        val items: Page<Item> = repository.FindItemByName(itemName, pageable)
        val itemsVOList: List<ItemsVO> = items.map { i -> DozerMapper.parseObject(i, ItemsVO::class.java) }.toList()

        return PagedResponse(
            content = itemsVOList,
            page = items.number,
            size = items.size,
            totalElements = items.totalElements,
            totalPages = items.totalPages
        )

    }
}