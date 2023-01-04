package fr.android.escoffier_lhoutellier.views

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import fr.android.escoffier_lhoutellier.R
import fr.android.escoffier_lhoutellier.adapters.CartRecyclerViewAdapter
import fr.android.escoffier_lhoutellier.data.Book
import fr.android.escoffier_lhoutellier.data.Cart

class CartActivity : AppCompatActivity() {
    fun associateQuantity(books: List<Book>): Map<String, Int> {
        val bookCounts = mutableMapOf<String, Int>()
        for (book in books) {
            if (bookCounts.containsKey(book.title)) {
                bookCounts[book.title] = bookCounts[book.title]!! + 1
            } else {
                bookCounts[book.title] = 1
            }
        }
        return bookCounts
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        println("onCreate CartActivity")
        setContentView(R.layout.activity_cart)
        val cart: Cart = intent.getParcelableExtra(BookActivity.CART)!!
        println("carts: " + cart.getBooks().size)
        val recyclerview = findViewById<RecyclerView>(R.id.cartList)
        val purchaseButton = findViewById<Button>(R.id.purchase)
        val books = cart.getBooks()
        val booksWithQuantity = associateQuantity(books)
        for (book in books) {
            book.quantity = booksWithQuantity[book.title]!!
        }
        val deduplicateBooks = mutableSetOf<Book>()
        for (book in books) {
            deduplicateBooks.add(book)
        }
        val adapter = CartRecyclerViewAdapter(deduplicateBooks.toList())

        // Setting the Adapter with the recyclerview
        recyclerview.adapter = adapter

        purchaseButton.setOnClickListener {
            Toast.makeText(this, "Purchased", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}