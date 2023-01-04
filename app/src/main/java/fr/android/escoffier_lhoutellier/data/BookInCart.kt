package fr.android.escoffier_lhoutellier.data

import android.os.Parcel
import android.os.Parcelable

data class BookInCart(val book: Book, var quantity: Int): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readParcelable(Book::class.java.classLoader)!!,
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeParcelable(book, flags)
        parcel.writeInt(quantity)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<BookInCart> {
        override fun createFromParcel(parcel: Parcel): BookInCart {
            return BookInCart(parcel)
        }

        override fun newArray(size: Int): Array<BookInCart?> {
            return arrayOfNulls(size)
        }
    }
}