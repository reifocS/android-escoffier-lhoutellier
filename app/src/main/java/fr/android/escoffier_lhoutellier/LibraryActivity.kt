package fr.android.escoffier_lhoutellier

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.android.escoffier_lhoutellier.repositories.BookViewModel

data class LibraryState(
    val books: List<Book> = emptyList(),
    val isLoading: Boolean
)

class LibraryActivity : AppCompatActivity() {

    private val viewModel by viewModels<BookViewModel>()
    companion object {
        const val fromActivityRequest = 42
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        println("onCreate LibraryActivity")
        setContentView(R.layout.activity_library)
        val recyclerview = findViewById<RecyclerView>(R.id.bookList)

        // this creates a vertical layout Manager
        recyclerview.layoutManager = LinearLayoutManager(this)


        // This will pass the ArrayList to our Adapter
        val adapter = MyBookRecyclerViewAdapter(ArrayList())

        // Setting the Adapter with the recyclerview
        recyclerview.adapter = adapter

        //observe
        viewModel.state.observe(this) { state ->
            adapter.values = state.books
            adapter.notifyDataSetChanged()
        }

        viewModel.loadBooks();
    }

}

