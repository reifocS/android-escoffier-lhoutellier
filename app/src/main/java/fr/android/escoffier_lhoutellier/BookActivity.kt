package fr.android.escoffier_lhoutellier


import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class BookActivity : AppCompatActivity() {

    companion object {
        internal const val BOOK = "BOOK"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book)

        val book: Book = intent.getParcelableExtra(BOOK)!!

        val authorTextView = findViewById<TextView>(R.id.bookDescription)
        authorTextView.text = book.synopsis[0]
        val priceTextView = findViewById<TextView>(R.id.bookPrice)
        priceTextView.text = "${book.price}€"
        val buttonAddToCart = findViewById<Button>(R.id.addToCart)
        buttonAddToCart.setOnClickListener {
            Toast.makeText(this, R.string.added_to_cart, Toast.LENGTH_SHORT).show()
            finish()
        }
    }

}
