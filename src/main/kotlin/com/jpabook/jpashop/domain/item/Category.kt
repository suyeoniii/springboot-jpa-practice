package com.jpabook.jpashop.domain.item

import javax.persistence.*

@Entity
class Category(name: String, parent: Category?) {
    @Id
    @GeneratedValue
    @Column(name = "category_id")
    var id: Long? = null

    var name: String = name

    @ManyToMany
    @JoinTable(
        name = "category_item",
        joinColumns = [JoinColumn(name = "category_id")],
        inverseJoinColumns = [JoinColumn(name = "item_id")]
    )
    var items: MutableList<Item> = ArrayList()

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    var parent: Category? = parent

    @OneToMany(mappedBy = "parent")
    var child: MutableList<Category> = ArrayList()
}
