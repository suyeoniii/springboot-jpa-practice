package com.jpabook.jpashop.domain.item

import javax.persistence.DiscriminatorValue
import javax.persistence.Entity

@Entity
@DiscriminatorValue("B")
class Book(name: String, price: Int, stockQuantity: Int, author: String, isbn: String) :
    Item(name, price, stockQuantity) {
    var author: String = author
    var isbn: String = isbn
}
