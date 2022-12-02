package fr.android.escoffier_lhoutellier

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class BookAdapter(context: Context, var books: List<Book>) : BaseAdapter() {

    // create variable with LayoutInflater.from()
    private val inflater = LayoutInflater.from(context)

    private class ViewHolder(view: View) {
        val nameTextView: TextView = view.findViewById(R.id.nameTextView)
        val priceTextView: TextView = view.findViewById(R.id.priceTextView)
    }

    override fun getCount(): Int {
        return books.count() // return the number of books
    }

    override fun getItem(position: Int): Book {
        return books[position] // return the book at the specified position
    }

    override fun getItemId(position: Int): Long {
        return getItem(position).hashCode().toLong() // return the position as a long value
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {
        // inflate the layout for each list item
        val view = convertView ?: inflater.inflate(R.layout.custom_view_item_book, parent, false)

        // get the current book
        val book = books[position]

        // use the ViewHolder pattern to avoid calling findViewById() multiple times
        val holder = view.tag as? ViewHolder ?: ViewHolder(view)
        view.tag = holder

        // set the values for the elements in the layout
        holder.nameTextView.text = book.title
        holder.priceTextView.text = book.price

        return view
    }

}
