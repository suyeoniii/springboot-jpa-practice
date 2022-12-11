package com.jpabook.jpashop

import com.jpabook.jpashop.domain.*
import com.jpabook.jpashop.domain.item.Book
import com.jpabook.jpashop.repository.ItemRepository
import com.jpabook.jpashop.repository.MemberRepository
import com.jpabook.jpashop.repository.OrderRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime
import javax.annotation.PostConstruct


@Component
class InitDB(private val initService: InitService) {

    @PostConstruct
    fun init() {
        initService.dbInit1()
        initService.dbInit2()
    }

    @Component
    @Transactional
    class InitService {
        @Autowired
        private lateinit var memberRepository: MemberRepository

        @Autowired
        private lateinit var itemRepository: ItemRepository

        @Autowired
        private lateinit var orderRepository: OrderRepository

        fun dbInit1() {
            val member = createMember("userA", "서울", "화랑로, ", "123-123")

            val book1 = createBook("JPA1", 10000, 100, "저자명", "1")
            val book2 = createBook("JPA2", 20000, 100, "저자명", "2")

            val orderItem1 = OrderItem().createOrderItem(book1, 10000, 1)
            val orderItem2 = OrderItem().createOrderItem(book2, 40000, 2)
            val delivery = Delivery(member.address!!, null, DeliveryStatus.READY)
            val order =
                Order(LocalDateTime.now(), OrderStatus.ORDER).createOrder(member, delivery, orderItem1, orderItem2)
            orderRepository.save(order)

        }

        fun dbInit2() {
            val member = createMember("userB", "경기", "화랑로, ", "123-123")

            val book1 = createBook("SPRING1", 20000, 200, "저자명", "3")
            val book2 = createBook("SPRING2", 40000, 200, "저자명", "4")

            val orderItem1 = OrderItem().createOrderItem(book1, 10000, 1)
            val orderItem2 = OrderItem().createOrderItem(book2, 40000, 2)
            val delivery = Delivery(member.address!!, null, DeliveryStatus.READY)
            val order =
                Order(LocalDateTime.now(), OrderStatus.ORDER).createOrder(member, delivery, orderItem1, orderItem2)
            orderRepository.save(order)

        }

        private fun createBook(name: String, price: Int, stockQuantity: Int, author: String, isbn: String): Book {
            val book = Book(name, price, stockQuantity, author, isbn)
            itemRepository.save(book)
            return book
        }

        private fun createMember(name: String, city: String, street: String, zipCode: String): Member {
            val member = Member(name, Address(city, street, zipCode))
            memberRepository.save(member)
            return member
        }
    }
}