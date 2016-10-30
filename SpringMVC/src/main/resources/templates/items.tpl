layout 'main.tpl', title: 'Items', content: contents {
    div {
        h3("Shop")
        table {
            tbody {
                itemsCollection.each { item ->
                    tr {
                        td item.id
                        td item.name
                        td {
                            a(href: "/add/${item.id}") {
                                yield "Add"
                            }
                        }
                    }
                }
            }
        }
        h3("Basket contains $basket.items.size items")
    }
}
