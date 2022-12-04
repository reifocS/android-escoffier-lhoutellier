package fr.android.escoffier_lhoutellier

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import fr.android.escoffier_lhoutellier.databinding.FragmentItemBinding

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
            val fragment = BookFragment.newInstance(item)
            val fragmentTransaction =
                (holder.itemView.context as LibraryActivity).supportFragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.containerFrameLayout, fragment)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            fragmentTransaction.commit()
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