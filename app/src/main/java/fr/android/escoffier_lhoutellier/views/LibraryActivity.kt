package fr.android.escoffier_lhoutellier.views

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.android.escoffier_lhoutellier.R
import fr.android.escoffier_lhoutellier.adapters.MyBookRecyclerViewAdapter
import fr.android.escoffier_lhoutellier.data.Book
import fr.android.escoffier_lhoutellier.data.BookInCart
import fr.android.escoffier_lhoutellier.data.Cart
import fr.android.escoffier_lhoutellier.repositories.BookViewModel

class LibraryActivity : AppCompatActivity() {

    private val viewModel by viewModels<BookViewModel>()

    companion object {
        const val fromActivityRequest = 42
        const val fromCartRequest = 43
        const val cartKey = "cartState"

    }

    public override fun onSaveInstanceState(outState: Bundle) {
        outState.putParcelable(cartKey, viewModel.state.value?.cart!!)
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        savedInstanceState.getParcelable<Cart>(cartKey)?.let {
            viewModel.setCart(it)
        }

    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        println("onCreate LibraryActivity")
        setContentView(R.layout.activity_library)
        val recyclerview = findViewById<RecyclerView>(R.id.bookList)

        // this creates a vertical layout Manager
        recyclerview.layoutManager = LinearLayoutManager(this)

        val cartImageView = findViewById<ImageView>(R.id.cart_icon)
        val context = this@LibraryActivity
        cartImageView.setOnClickListener {
            val myIntent = Intent(context, CartActivity::class.java)
            myIntent.putExtra(BookActivity.CART, viewModel.state.value?.cart)
            context.startActivityForResult(myIntent, fromCartRequest)
        }
        // This will pass the ArrayList to our Adapter
        val adapter = MyBookRecyclerViewAdapter(ArrayList())


        // Setting the Adapter with the recyclerview
        recyclerview.adapter = adapter
        val itemCountTextView = findViewById<TextView>(R.id.item_count)
        //observe
        viewModel.state.observe(this) { state ->
            adapter.values = state.books
            adapter.notifyDataSetChanged()
            itemCountTextView.text = state.cart.getBooks().sumOf { it.quantity }.toString()
        }

        try {
            viewModel.loadBooks()
        } catch (e: Exception) {
            // Handle exceptions thrown by the ViewModel
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        println("OnActivityResult")
        val cart = viewModel.state.value?.cart!!
        println("${cart.getBooks()}, $resultCode, $requestCode")
        if (requestCode == fromActivityRequest) {
            val bookFromActivity = data?.getParcelableExtra<Book>(BookActivity.CART)
            if (bookFromActivity != null) {
                var found = false
                for (bookInCart in cart.getBooks()) {
                    if (bookInCart.book.title == bookFromActivity.title) {
                        bookInCart.quantity += 1
                        found = true
                    }
                }
                if (!found) {
                    val newEntry = BookInCart(bookFromActivity, 1)
                    cart.add(newEntry)
                }
                viewModel.setCart(cart)

            }
        } else if (requestCode == fromCartRequest) {
            val cartFromActivity = data?.getParcelableExtra<Cart>(CartActivity.NEWCART)
            if (cartFromActivity != null) {
                viewModel.setCart(cartFromActivity)
            }
        }
    }
}

