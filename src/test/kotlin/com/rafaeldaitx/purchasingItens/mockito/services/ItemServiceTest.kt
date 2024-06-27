package com.rafaeldaitx.purchasingItens.mockito.services

import com.rafaeldaitx.purchasingItens.mock.ItemMock
import com.rafaeldaitx.purchasingItens.repository.ItemRepository
import com.rafaeldaitx.purchasingItens.service.ItemService
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.junit.jupiter.MockitoExtension
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import java.util.*

@ExtendWith(MockitoExtension::class)
internal class ItemServiceTest {

    private lateinit var inputObject: ItemMock

    @InjectMocks
    private lateinit var service: ItemService

    @Mock
    private lateinit var repository: ItemRepository

    @BeforeEach
    fun setupMock(){
        inputObject = ItemMock()
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun findAll(){
        val list = inputObject.mockEntityList()
        `when`(repository.findAll()).thenReturn(list)

        val items = service.findAll()

        assertNotNull(items)

        val itemOne = items[1]

        assertNotNull(itemOne)
        assertNotNull(itemOne.key)
        assertNotNull(itemOne.itemName)
        assertNotNull(itemOne.marketplace)
        assertNotNull(itemOne.link)
        assertNotNull(itemOne.price)

        val itemNine = items[9]

        assertNotNull(itemNine)
        assertNotNull(itemNine.key)
        assertNotNull(itemNine.itemName)
        assertNotNull(itemNine.marketplace)
        assertNotNull(itemNine.link)
        assertNotNull(itemNine.price)
    }
    
    @Test
    fun findViewById(){
        //Executa a chamada no service, e la quando chama o repository,
        //MockitoAnnotations.openMocks(this) garante que o acesso ao repository
        //seja feeito pelo mockito, e não direto no banco de dados real
        val items = inputObject.mockEntity(1)
        items.id = 1L
        `when`(repository.findById(1)).thenReturn(Optional.of(items))

        val result = service.findById(1)

        assertNotNull(result)
        assertNotNull(result.key)
        assertNotNull(result.itemName)
        assertNotNull(result.marketplace)
        assertNotNull(result.link)
        assertNotNull(result.price)

        assertEquals("Item name 1", result.itemName)
        assertEquals("Marketplace 1", result.marketplace)
        assertEquals("https://github.com/RafaelDaitx/SpringJavaRest/commit/409dfab97a52ec0209748177cd1fb5fdce1172b0#diff-007581ff50f01b5caadc2ef80e77371dbc8c28ca068172c50f4db31fc66cf4781", result.link)
        assertEquals(20000.0, result.price)
    }

    @Test
    fun create(){
        val entity = inputObject.mockEntity(1)

        val persisted = entity.copy(1)
        
        `when`(repository.save(entity)).thenReturn(persisted)
        
        val vo = inputObject.mockVO(1)
        val result = service.create(vo)

        assertNotNull(result)
        assertNotNull(result.key)
        assertNotNull(result.itemName)
        assertNotNull(result.marketplace)
        assertNotNull(result.link)
        assertNotNull(result.price)

        assertEquals("Item name 1", result.itemName)
        assertEquals("Marketplace 1", result.marketplace)
        assertEquals("https://github.com/RafaelDaitx/SpringJavaRest/commit/409dfab97a52ec0209748177cd1fb5fdce1172b0#diff-007581ff50f01b5caadc2ef80e77371dbc8c28ca068172c50f4db31fc66cf4781", result.link)
        assertEquals(20000.0, result.price)
    }

    @Test
    fun update(){
        val entity = inputObject.mockEntity(1)

        val persisted = entity.copy()
        persisted.id = 3
        persisted.itemName = "altered"
        persisted.marketplace = "MarketplaceAltered"
        persisted.link = "https://github.com/RafaelDaitx/SpringJavaRest/commit/409dfab97a52ec0209748177cd1fb5fdce1172b0#diff-007581ff50f01b5caadc2ef80e77371dbc8c28ca068172c50f4db31fc66cf4782"
        persisted.price = 20.0
        //Optional, se não retoranr nada, retorna um vazio. (de um clique sobre ele)
        `when`(repository.findById(3)).thenReturn(Optional.of(entity))
        `when`(repository.save(entity)).thenReturn(persisted)

        val result = service.update( persisted)
        assertNotNull(result)
        assertNotNull(result.id)
        assertNotNull(result.marketplace)
        assertNotNull(result.link)
        assertNotNull(result.price)

        assertEquals("altered", result.itemName)
        assertEquals("MarketplaceAltered", result.marketplace)
        assertEquals("https://github.com/RafaelDaitx/SpringJavaRest/commit/409dfab97a52ec0209748177cd1fb5fdce1172b0#diff-007581ff50f01b5caadc2ef80e77371dbc8c28ca068172c50f4db31fc66cf4782", persisted.link)
        assertEquals(20.0, result.price)
    }

    @Test
    fun delete() {
        val entity = inputObject.mockEntity(1)

        `when`(repository.findById(1)).thenReturn(Optional.of(entity))
        service.delete(1)
    }
    
    
}