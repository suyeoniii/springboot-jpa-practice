package com.jpabook.jpashop.domain.item

import javax.persistence.*

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
abstract class Item(name: String, price: Int, stockQuantity: Int) {
    @Id
    @GeneratedValue
    @Column(name = "item_id")
    var id: Long? = null

    var name: String = name
    var price: Int = price
    var stockQuantity: Int = stockQuantity

    @ManyToMany(mappedBy = "items")
    var categories: MutableList<Category> = ArrayList()

    // ==비즈니스 로직== //
    /**
     *  stock 증가
     */
    fun addStock(quantity: Int) {
        this.stockQuantity += quantity
    }

    /**
     *  stock 증가
     */
    fun removeStock(quantity: Int) {
        val restStock = this.stockQuantity - quantity
        if (restStock < 0) {
            // throw NotEnoughStockException("need more stock")
        }
        this.stockQuantity = restStock
    }
}
