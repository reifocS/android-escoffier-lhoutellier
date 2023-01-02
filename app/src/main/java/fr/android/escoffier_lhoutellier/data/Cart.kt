package fr.android.escoffier_lhoutellier.data

class Cart {
    private val books: MutableList<Book> = ArrayList()
    fun getBooks(): List<Book> {
        return books
    }

    fun add(book: Book) {
        books.add(book)
    }

    fun remove(book: Book) {
        books.remove(book)
    }
}