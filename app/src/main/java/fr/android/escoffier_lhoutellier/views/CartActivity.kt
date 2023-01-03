package fr.android.escoffier_lhoutellier.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import fr.android.escoffier_lhoutellier.R
import fr.android.escoffier_lhoutellier.adapters.CartRecyclerViewAdapter
import fr.android.escoffier_lhoutellier.data.Cart

class CartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        println("onCreate CartActivity")
        setContentView(R.layout.activity_cart)
        val cart: Cart = intent.getParcelableExtra(BookActivity.CART)!!
        println("carts: " + cart.getBooks().size)
        val recyclerview = findViewById<RecyclerView>(R.id.cartList)

        val adapter = CartRecyclerViewAdapter(cart.getBooks())

        // Setting the Adapter with the recyclerview
        recyclerview.adapter = adapter
    }
}