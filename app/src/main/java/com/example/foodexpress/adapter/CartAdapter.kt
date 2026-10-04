package com.example.foodexpress.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.foodexpress.CartManager
import com.example.foodexpress.R
import com.example.foodexpress.model.CartItem

class CartAdapter(
    private var items: List<CartItem>,
    private val onChanged: () -> Unit
) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    class CartViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val emoji: TextView = view.findViewById(R.id.cartEmoji)
        val name: TextView = view.findViewById(R.id.cartName)
        val price: TextView = view.findViewById(R.id.cartPrice)
        val quantity: TextView = view.findViewById(R.id.cartQuantity)
        val minus: Button = view.findViewById(R.id.minusButton)
        val plus: Button = view.findViewById(R.id.plusButton)
        val remove: Button = view.findViewById(R.id.removeButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_cart, parent, false)
        return CartViewHolder(view)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val item = items[position]

        holder.emoji.text = item.food.emoji
        holder.name.text = item.food.name
        holder.price.text = "₹${item.food.price * item.quantity}"
        holder.quantity.text = item.quantity.toString()

        holder.plus.setOnClickListener {
            CartManager.increase(item.food.id)
            refresh()
        }

        holder.minus.setOnClickListener {
            CartManager.decrease(item.food.id)
            refresh()
        }

        holder.remove.setOnClickListener {
            CartManager.remove(item.food.id)
            refresh()
        }
    }

    private fun refresh() {
        items = CartManager.getItems()
        notifyDataSetChanged()
        onChanged()
    }

    override fun getItemCount(): Int = items.size
}
