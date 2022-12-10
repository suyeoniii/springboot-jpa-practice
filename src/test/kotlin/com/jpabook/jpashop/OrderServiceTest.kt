package com.jpabook.jpashop

import com.jpabook.jpashop.domain.Address
import com.jpabook.jpashop.domain.Member
import com.jpabook.jpashop.domain.OrderStatus
import com.jpabook.jpashop.domain.item.Book
import com.jpabook.jpashop.exception.NotEnoughStockException
import com.jpabook.jpashop.repository.ItemRepository
import com.jpabook.jpashop.repository.MemberRepository
import com.jpabook.jpashop.repository.OrderRepository
import com.jpabook.jpashop.service.OrderService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import javax.transaction.Transactional


@SpringBootTest
@Transactional
class OrderServiceTest {

    @Autowired
    private lateinit var memberRepository: MemberRepository

    @Autowired
    private lateinit var orderService: OrderService

    @Autowired
    private lateinit var itemRepository: ItemRepository

    @Autowired
    private lateinit var orderRepository: OrderRepository

    @Test
    fun 상품주문() {
        // given
        val member = createMember("김누구")
        val book = createBook("시골 JPA", 10000, 10, "김영한", "isbn?")

        val orderCount = 2

        // when
        val orderId = orderService.order(member.id!!, book.id!!, orderCount)

        // then
        val getOrder = orderRepository.findOneById(orderId!!)
        assertEquals(OrderStatus.ORDER, getOrder!!.status)
        assertEquals(1, getOrder.orderItems.size)
        assertEquals(10000 * orderCount, getOrder.getTotalPrice())
        assertEquals(10 - orderCount, book.stockQuantity)
    }

    private fun createBook(name: String, price: Int, stockQuantity: Int, author: String, isbn: String): Book {
        val book = Book(name, price, stockQuantity, author, isbn)
        itemRepository.save(book)
        return book
    }

    private fun createMember(name: String): Member {
        val member = Member(name, Address("서울", "강가", "123-123"))
        memberRepository.save(member)
        return member
    }

    @Test
    fun 상품주문_재고수량초과() {
        // given
        val member = createMember("김누구")
        val book = createBook("시골 JPA", 10000, 10, "김영한", "isbn?")

        val orderCount = 11

        // when
        // then
        assertThrows(NotEnoughStockException::class.java) { orderService.order(member.id!!, book.id!!, orderCount) }
    }


    @Test
    fun 상품취소() {
        // given
        val member = createMember("김누구")
        val book = createBook("시골 JPA", 10000, 10, "김영한", "isbn?")

        val orderCount = 2
        val orderId = orderService.order(member.id!!, book.id!!, orderCount)

        // when
        orderService.cancelOrder(orderId!!)

        // then
        assertEquals(OrderStatus.CANCELD, orderRepository.findOneById(orderId)!!.status)
        assertEquals(10, book.stockQuantity)
    }
}