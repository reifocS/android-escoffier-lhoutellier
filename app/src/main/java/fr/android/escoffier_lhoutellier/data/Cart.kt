package fr.android.escoffier_lhoutellier.data

import android.os.Parcel
import android.os.Parcelable

class Cart() : Parcelable {
    private var books: MutableList<BookInCart> = ArrayList()

    constructor(parcel: Parcel) : this() {
        parcel.readList(books, Book::class.java.classLoader)
    }

    fun getBooks(): List<BookInCart> {
        return books
    }

    fun add(book: BookInCart) {
        books.add(book)
    }

    fun remove(book: BookInCart) {
        books.remove(book)
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeList(books)
    }

    override fun describeContents(): Int {
        return 0
    }

    fun empty() {
        books.clear()
    }

    companion object CREATOR : Parcelable.Creator<Cart> {
        override fun createFromParcel(parcel: Parcel): Cart {
            return Cart(parcel)
        }

        override fun newArray(size: Int): Array<Cart?> {
            return arrayOfNulls(size)
        }
    }
}