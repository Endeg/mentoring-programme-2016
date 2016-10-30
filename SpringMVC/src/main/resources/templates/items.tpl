html {
    head {
        title(title)
    }
    body {
        div(class: 'container') {
            h1(title)
            div {
                h2("Shop")
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
                h2("Basket contains $basket.items.size items")
            }
        }
    }
}
