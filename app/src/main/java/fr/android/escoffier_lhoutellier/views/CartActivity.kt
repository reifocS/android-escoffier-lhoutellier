package fr.android.escoffier_lhoutellier.views

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import fr.android.escoffier_lhoutellier.R
import fr.android.escoffier_lhoutellier.adapters.CartRecyclerViewAdapter
import fr.android.escoffier_lhoutellier.data.Book
import fr.android.escoffier_lhoutellier.data.BookInCart
import fr.android.escoffier_lhoutellier.data.Cart

class CartActivity : AppCompatActivity() {
    companion object {
        internal const val NEWCART = "NEWCART"
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
        val adapter = CartRecyclerViewAdapter(books)

        // Setting the Adapter with the recyclerview
        recyclerview.adapter = adapter

        purchaseButton.setOnClickListener {
            Toast.makeText(this, "Purchased", Toast.LENGTH_SHORT).show()
            val intentCart = Intent()
            cart.empty()
            intentCart.putExtra(NEWCART, cart)
            setResult(RESULT_OK, intentCart)
            finish()
        }
    }
}