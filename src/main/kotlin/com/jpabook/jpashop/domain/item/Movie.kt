package com.jpabook.jpashop.domain.item

import javax.persistence.DiscriminatorValue
import javax.persistence.Entity

@Entity
@DiscriminatorValue("M")
class Movie(name: String, price: Int, stockQuantity: Int, director: String, actor: String) :
    Item(name, price, stockQuantity) {
    var director: String = director
    var actor: String = actor
}
