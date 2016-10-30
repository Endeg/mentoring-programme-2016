html {
    head {
        title(title)
    }
    body {
        div(class:'container') {
            h1(title)
            div {
                items.each { item ->
                    tr {
                        td ${item.id}
                        td ${item.name}
                        td {
                            a(href:"/add/${item.id}") {
                                yield "Add"
                            }
                        }
                    }
                }
            }
        }
    }
}
