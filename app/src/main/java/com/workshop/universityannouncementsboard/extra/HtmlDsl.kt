package com.workshop.universityannouncementsboard.extra

/** Also see [com.workshop.universityannouncementsboard.AnnouncementsConfig] */
fun main() {
    println(createTable())
    /*
        <table><tr><td>A</td><td>B</td></tr><tr><td>C</td><td>D</td></tr></table>
            OR
        <table>
        <tr>
        <td>A</td>
        <td>B</td>
        </tr>
        <tr>
        <td>C</td>
        <td>D</td>
        </tr>
        </table>
     */
}

fun createTable(): TableBuilder {
    return table {
        tr {
            td { +"A" }
            td { +"B" }
            // Without htmlDsl it will be allowed
//            tr {
//                td { +"C" }
//                td { +"D" }
//            }
        }
        tr {
            td { +"C" }
            td { +"D" }
        }
    }
}

fun table(init: TableBuilder.() -> Unit): TableBuilder {
    val tableBuilder = TableBuilder().apply { init() }
//    init.invoke(tableBuilder)
    return tableBuilder
}

@HtmlDsl
data class TableBuilder(var trs: List<TrBuilder> = emptyList()) {
    override fun toString(): String = "<table>${trs.joinToString(separator = "")}</table>"

    fun tr(init: TrBuilder.() -> Unit) {
        val builder = TrBuilder().apply { init() }
//        init.invoke(builder)
        trs += builder
    }
}

@HtmlDsl
data class TrBuilder(var tds: List<TdBuilder> = emptyList()) {
    override fun toString(): String = "<tr>${tds.joinToString(separator = "")}</tr>"

    fun td(init: TdBuilder.() -> Unit) {
        val builder = TdBuilder().apply { init() }
//        init.invoke(builder)
        tds += builder
    }
}

@HtmlDsl
data class TdBuilder(var text: String = "") {
    override fun toString(): String = "<td>$text</td>"

    operator fun String.unaryPlus() {
        text += this
    }
}

@DslMarker
annotation class HtmlDsl