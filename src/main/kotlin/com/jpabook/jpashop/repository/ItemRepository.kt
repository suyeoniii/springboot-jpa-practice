package com.jpabook.jpashop.repository

import com.jpabook.jpashop.domain.item.Item
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ItemRepository : JpaRepository<Item, Long> {
    fun findItem(itemId: Long): Item?
}