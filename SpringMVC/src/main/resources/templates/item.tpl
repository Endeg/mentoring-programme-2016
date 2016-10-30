layout 'main.tpl', title: 'View item', content: contents {
    div {
        table {
            tbody {
                tr {
                    td "Id"
                    td itemObject.id
                }
                tr {
                    td "Name"
                    td itemObject.name
                }
            }
        }
    }
}
