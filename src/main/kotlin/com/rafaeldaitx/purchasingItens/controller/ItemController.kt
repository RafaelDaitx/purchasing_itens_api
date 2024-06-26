package com.rafaeldaitx.purchasingItens.controller

import com.rafaeldaitx.purchasingItens.data.vo.v1.ItemsVO
import com.rafaeldaitx.purchasingItens.model.Item
import com.rafaeldaitx.purchasingItens.service.ItemService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class ItemController {

    @Autowired
    private lateinit var service: ItemService

    @GetMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    fun findAll(): List<ItemsVO>{
        return service.findAll();
    }


    @RequestMapping("/{id}", method = [RequestMethod.GET],
        produces = [MediaType.APPLICATION_JSON_VALUE])
    fun findById(@PathVariable("id") id: Long): ItemsVO{
        return service.findById(id)
    }
}