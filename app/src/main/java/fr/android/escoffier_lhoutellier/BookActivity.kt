package fr.android.escoffier_lhoutellier


import android.os.Bundle
import android.widget.TextView
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
    }

}
