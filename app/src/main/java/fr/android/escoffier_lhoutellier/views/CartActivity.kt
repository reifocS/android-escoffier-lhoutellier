package fr.android.escoffier_lhoutellier.views

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.DisplayMetrics
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.android.escoffier_lhoutellier.R
import fr.android.escoffier_lhoutellier.adapters.CartRecyclerViewAdapter
import fr.android.escoffier_lhoutellier.adapters.OnItemClickListener
import fr.android.escoffier_lhoutellier.data.Cart

class CartActivity : AppCompatActivity(), OnItemClickListener {
    companion object {
        internal const val NEWCART = "NEWCART"
    }

    var cart: Cart = Cart()
    private lateinit var adapter: CartRecyclerViewAdapter

    override fun onBackPressed() {
        val intentCart = Intent()
        intentCart.putExtra(NEWCART, cart)
        setResult(RESULT_OK, intentCart)
        super.onBackPressed()
    }

    fun calculateNoOfColumns(
        context: Context,
        columnWidthDp: Double
    ): Int { // For example columnWidthdp=180
        val displayMetrics: DisplayMetrics = context.resources.displayMetrics
        val screenWidthDp = displayMetrics.widthPixels / displayMetrics.density
        return (screenWidthDp / columnWidthDp + 0.5).toInt()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        println("onCreate CartActivity")
        setContentView(R.layout.activity_cart)
        cart = intent.getParcelableExtra(BookActivity.CART)!!
        println("carts: " + cart.getBooks().size)
        val recyclerview = findViewById<RecyclerView>(R.id.cartList)
        recyclerview.layoutManager =
            GridLayoutManager(this, calculateNoOfColumns(applicationContext, 180.0))

        val purchaseButton = findViewById<Button>(R.id.purchase)
        val books = cart.getBooks()
        adapter = CartRecyclerViewAdapter(books)
        adapter.setOnItemClickListener(this)

        // Setting the Adapter with the recyclerview
        recyclerview.adapter = adapter
        purchaseButton.text = "Purchase for ${
            cart.getBooks().sumOf { it.quantity.toDouble() * it.book.price.toDouble() }
        }€"

        purchaseButton.setOnClickListener {
            if (cart.getBooks().isEmpty()) {
                Toast.makeText(
                    this,
                    "Need to have at least one article in cart",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                Toast.makeText(
                    this,
                    "Purchased ${cart.getBooks().sumOf { it.quantity }} article",
                    Toast.LENGTH_SHORT
                ).show()
                cart.empty()
                val intentCart = Intent()
                intentCart.putExtra(NEWCART, cart)
                setResult(RESULT_OK, intentCart)
                finish()
            }
        }
    }

    override fun onItemClick(position: Int) {
        println("$position ${cart.getBooks()}")
        val bookToRemove = cart.getBooks()[position]
        if (bookToRemove.quantity > 1) {
            bookToRemove.quantity -= 1
        } else {
            cart.remove(bookToRemove)
        }
        adapter.notifyDataSetChanged()
        val purchaseButton = findViewById<Button>(R.id.purchase)
        purchaseButton.text = "Purchase for ${
            cart.getBooks().sumOf { it.quantity.toDouble() * it.book.price.toDouble() }
        }€"
    }
}