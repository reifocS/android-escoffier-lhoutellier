package fr.android.escoffier_lhoutellier.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import fr.android.escoffier_lhoutellier.data.Book
import fr.android.escoffier_lhoutellier.databinding.FragmentItemBinding
import fr.android.escoffier_lhoutellier.views.BookActivity
import fr.android.escoffier_lhoutellier.views.LibraryActivity


class MyBookRecyclerViewAdapter(
    var values: List<Book>
) : RecyclerView.Adapter<MyBookRecyclerViewAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            FragmentItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.price.text = buildString {
            append(item.price)
            append("€")
        }
        holder.title.text = item.title
        Picasso
            .get()
            .load(item.cover)
            .into(holder.img)
        holder.img.setOnClickListener {
            // Create and show the fragment that shows the book synopsis
            val context = holder.itemView.context as LibraryActivity
            val intent = Intent(context, BookActivity::class.java)
            intent.putExtra(BookActivity.BOOK, item)
            context.startActivityForResult(intent, LibraryActivity.fromActivityRequest)
        }
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: FragmentItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val price: TextView = binding.bookPrice
        val title: TextView = binding.bookTitle
        val img: ImageView = binding.bookImage

        override fun toString(): String {
            return super.toString() + " '" + title.text + "'"
        }
    }

}