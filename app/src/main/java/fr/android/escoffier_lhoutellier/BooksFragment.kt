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

class BooksFragment : Fragment() {

    private val viewModel by viewModels<BookViewModel>()
    private var adapter = MyBookRecyclerViewAdapter(emptyList())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_item_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        println("On ViewCreated")

        super.onViewCreated(view, savedInstanceState)
        val recyclerView: RecyclerView = view.findViewById(R.id.bookList)
        recyclerView.layoutManager = LinearLayoutManager(this.context)
        recyclerView.adapter = adapter
        viewModel.state.observe(this) { state ->
            adapter.values = state.books
            adapter.notifyDataSetChanged()
        }
        viewModel.loadBooks()
    }

    override fun onPause() {
        super.onPause()
        println("On pause")
    }

    override fun onResume() {
        super.onResume()
        println("onResume")
    }

    override fun onDestroyView() {
        view?.findViewById<RecyclerView>(R.id.bookList)?.adapter = null
        super.onDestroyView()
    }
}