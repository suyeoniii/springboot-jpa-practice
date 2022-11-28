package com.jpabook.jpashop.domain.item

import javax.persistence.DiscriminatorValue
import javax.persistence.Entity

@Entity
@DiscriminatorValue("A")
class Album(name: String, price: Int, stockQuantity: Int, artist: String, etc: String) :
    Item(name, price, stockQuantity) {
    var artist: String = artist
    var etc: String = etc
}
