package fr.android.escoffier_lhoutellier

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.android.escoffier_lhoutellier.repositories.BookViewModel


/**
 * A fragment representing a list of Items.
 */
class BookFragment : Fragment() {

    private val viewModel by viewModels<BookViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: RecyclerView =
            inflater.inflate(R.layout.fragment_item_list, container, false) as RecyclerView
        // Set the adapter

        view.layoutManager = LinearLayoutManager(context)
        val adapter = MyBookRecyclerViewAdapter(emptyList())
        view.adapter = adapter

        viewModel.state.observe(this) { state ->
            adapter.values = state.books
            adapter.notifyDataSetChanged()
        }
        viewModel.loadBooks()
        return view
    }
}