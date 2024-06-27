package com.rafaeldaitx.purchasingItens.mock

import com.rafaeldaitx.purchasingItens.data.vo.v1.ItemsVO
import com.rafaeldaitx.purchasingItens.model.Item

class ItemMock {

    fun mockEntity(): Item{
        return mockEntity(0)
    }

    fun mockVO(): ItemsVO{
        return mockVO(0)
    }

    fun mockEntityList(): ArrayList<Item>{
        val items: ArrayList<Item> = ArrayList<Item>()
        for (i in 0..15){
            items.add(mockEntity(i))
        }
        return items
    }

    fun mockEntity(number: Int): Item {
        val item = Item()
        item.itemName = "Item name $number"
        item.marketplace = "Marketplace $number"
        item.link = "https://github.com/RafaelDaitx/SpringJavaRest/commit/409dfab97a52ec0209748177cd1fb5fdce1172b0#diff-007581ff50f01b5caadc2ef80e77371dbc8c28ca068172c50f4db31fc66cf478${number}"
        item.price = 20000.0
        return item
    }

    fun mockVO(number: Int): ItemsVO {
        val item = ItemsVO()
        item.itemName = "Item name $number"
        item.marketplace = "Marketplace $number"
        item.link = "https://github.com/RafaelDaitx/SpringJavaRest/commit/409dfab97a52ec0209748177cd1fb5fdce1172b0#diff-007581ff50f01b5caadc2ef80e77371dbc8c28ca068172c50f4db31fc66cf478${number}"
        item.price = 20000.0
        return item
    }
}