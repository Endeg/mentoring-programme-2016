html {
    head {
        title(title)
    }
    body {
        div {
            p {
                a(href: "/") {
                    yield "Items"
                }
                yield " | "
                a(href: "/basket") {
                    yield "Basket"
                }
            }

            h2(title)
            div { content() }
        }
    }
}
