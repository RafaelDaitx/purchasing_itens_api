package com.rafaeldaitx.purchasingItens.mapper.custom

import com.rafaeldaitx.purchasingItens.data.vo.v1.ItemsVO
import com.rafaeldaitx.purchasingItens.model.Item

class ItemMapper {

    fun mapEntityToVO(item: Item): ItemsVO{
        val vo = ItemsVO()

        vo.key = item.id
        vo.itemName = item.itemName
        vo.marketplace = item.marketplace
        vo.link = item.marketplace
        vo.price = item.price

        return vo
    }

    fun mapVOToEntity(item: ItemsVO): Item{
        val entity = Item()

        entity.id = item.key
        entity.itemName = item.itemName
        entity.marketplace = item.marketplace
        entity.link = item.marketplace
        entity.price = item.price

        return entity
    }
}