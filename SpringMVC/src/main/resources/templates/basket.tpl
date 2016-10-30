layout 'main.tpl', title: 'Basket', content: contents {
    div {
        table {
            tbody {
                basketObject.items.each { item ->
                    tr {
                        td item.id
                        td item.name
                        td {
                            a(href: "/remove/${item.id}") {
                                yield "Remove"
                            }
                        }
                    }
                }
            }
        }
        h3("Basket contains $basketObject.items.size items")
    }
}
