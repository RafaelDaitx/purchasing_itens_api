package com.rafaeldaitx.purchasingItens.controller

import com.rafaeldaitx.purchasingItens.data.vo.v1.ItemsVO
import com.rafaeldaitx.purchasingItens.model.Item
import com.rafaeldaitx.purchasingItens.model.PagedResponse
import com.rafaeldaitx.purchasingItens.service.ItemService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.ArraySchema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.hateoas.EntityModel
import org.springframework.hateoas.PagedModel
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class ItemController {

    @Autowired
    private lateinit var service: ItemService

    @GetMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    @Operation(
        summary = "Finds all item", description = "Finds all item",
        tags = ["Item"],
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
    fun findAll(): List<ItemsVO> {
        return service.findAll();
    }

    @GetMapping(value = ["/findItemByName/{itemName}"],produces = [MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE])
    @Operation(
        summary = "Finds items by name", description = "Finds  items by name",
        tags = ["Item"],
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
    fun findByName( @PathVariable(value = "itemName") itemName: String,
        @RequestParam(value = "page", defaultValue = "0") page: Int,
        @RequestParam(value = "size", defaultValue = "12") size: Int
    ):  ResponseEntity<PagedResponse<ItemsVO>>{
        val pageable: Pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "itemName"))//qual campo vai ser ordenado, de acordo com a model

        val items = service.findByName(itemName, pageable)

        return ResponseEntity.ok(items)
    }

    @RequestMapping(
        "/{id}", method = [RequestMethod.GET],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    @Operation(
        summary = "Finds a item", description = "Finds a item",
        tags = ["Item"],
        responses = [
            ApiResponse(
                description = "Success",
                responseCode = "200",
                content = [
                    Content(schema = Schema(implementation = ItemsVO::class))
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
    fun findById(@PathVariable("id") id: Long): ItemsVO {
        return service.findById(id)
    }

    @PostMapping(
        consumes = [MediaType.APPLICATION_JSON_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    @Operation(
        summary = "Create a new item", description = "Create a new item",
        tags = ["Item"],
        responses = [
            ApiResponse(
                description = "Success",
                responseCode = "200",
                content = [
                    Content(schema = Schema(implementation = ItemsVO::class))
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
    fun create(@RequestBody item: ItemsVO): ItemsVO {
        return service.create(item)
    }

    @PutMapping(
        consumes = [MediaType.APPLICATION_JSON_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    @Operation(
        summary = "Updates a Item", description = "Üpdates a Item",
        tags = ["Item"],
        responses = [
            ApiResponse(
                description = "Success",
                responseCode = "200",
                content = [
                    Content(schema = Schema(implementation = ItemsVO::class))
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
                description = "Not Found", responseCode = "401", content =
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
    fun update(@RequestBody item: Item): Item {
        return service.update(item)
    }

    @DeleteMapping("/{id}", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun delete(@PathVariable("id") id: Long): ResponseEntity<*> {
        service.delete(id)
        return ResponseEntity.noContent().build<Any>()
    }
}