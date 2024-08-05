package com.rafaeldaitx.purchasingItens.controller

import com.rafaeldaitx.purchasingItens.data.vo.v1.CategoriesVO
import com.rafaeldaitx.purchasingItens.data.vo.v1.ItemsVO
import com.rafaeldaitx.purchasingItens.model.categories.Categories
import com.rafaeldaitx.purchasingItens.service.CategoriesService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.ArraySchema
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/categories")
class CategoriesController {

    @Autowired
    private lateinit var service: CategoriesService

    @PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun create(@RequestBody category: CategoriesVO): CategoriesVO{
        return service.create(category);
    }

    @GetMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    @Operation(
        summary = "It finds all categories", description = "It finds all categories",
        tags = ["Categories"],
        responses = [
            ApiResponse(
                description = "Success",
                responseCode = "200",
                content = [
                    Content(array = ArraySchema(schema = Schema(implementation = ItemsVO::class)))
                ]
            ),
            ApiResponse(
                description = "No Content", responseCode = "204", content =
                [
                    Content(schema = Schema(implementation = Unit::class))
                ]
            ),
            ApiResponse(
                description = "Bad Request", responseCode = "400", content =
                [
                    Content(schema = Schema(implementation = Unit::class))
                ]
            ),
            ApiResponse(
                description = "Unauthorized", responseCode = "401", content =
                [
                    Content(schema = Schema(implementation = Unit::class))
                ]
            ),
            ApiResponse(
                description = "Not found", responseCode = "404", content =
                [
                    Content(schema = Schema(implementation = Unit::class))
                ]
            ),
            ApiResponse(
                description = "Internal Error", responseCode = "500", content =
                [
                    Content(schema = Schema(implementation = Unit::class))
                ]
            ),
        ]
    )
    fun findAll():List<CategoriesVO>{
        return service.findAll()
    }

    @RequestMapping(
        "/{id}", method = [RequestMethod.GET],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun findById(@PathVariable("id") id: Long): CategoriesVO{
        return service.findById(id)
    }

    @DeleteMapping("/{id}", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun delete(@PathVariable("id") id: Long): ResponseEntity<*>{
        service.delete(id)
        return ResponseEntity.noContent().build<Any>()
    }
}