package fr.android.escoffier_lhoutellier.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import fr.android.escoffier_lhoutellier.data.BookInCart
import fr.android.escoffier_lhoutellier.databinding.FragmentCartBinding

interface OnItemClickListener {
    fun onItemClick(position: Int)
}

class CartRecyclerViewAdapter(
    var values: List<BookInCart>
) : RecyclerView.Adapter<CartRecyclerViewAdapter.ViewHolder>() {

    private var listener: OnItemClickListener? = null

    fun setOnItemClickListener(listener: OnItemClickListener?) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            FragmentCartBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.price.text = buildString {
            append(item.book.price)
            append("€")
        }
        holder.title.text = item.book.title
        holder.qty.text = "Quantity: ${item.quantity}"
        holder.removeButton.setOnClickListener {
            listener!!.onItemClick(position)
        }
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: FragmentCartBinding) : RecyclerView.ViewHolder(binding.root) {
        val price: TextView = binding.bookCartPrice
        val title: TextView = binding.bookCartTitle
        val qty: TextView = binding.bookCartQuantity
        val removeButton: Button = binding.buttonRemove

        override fun toString(): String {
            return super.toString() + " '" + title.text + "'"
        }
    }

}