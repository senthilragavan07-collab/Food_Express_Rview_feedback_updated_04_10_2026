package com.example.foodexpress.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.foodexpress.R
import com.example.foodexpress.model.FoodItem

class FoodAdapter(
    private val foodList: List<FoodItem>,
    private val onAddToCart: (FoodItem) -> Unit
) : RecyclerView.Adapter<FoodAdapter.FoodViewHolder>() {

    class FoodViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val emoji: TextView = view.findViewById(R.id.foodEmoji)
        val name: TextView = view.findViewById(R.id.foodName)
        val description: TextView = view.findViewById(R.id.foodDescription)
        val price: TextView = view.findViewById(R.id.foodPrice)
        val addButton: Button = view.findViewById(R.id.addButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_food, parent, false)
        return FoodViewHolder(view)
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        val food = foodList[position]
        holder.emoji.text = food.emoji
        holder.name.text = food.name
        holder.description.text = food.description
        holder.price.text = "₹${food.price}"

        holder.addButton.setOnClickListener {
            onAddToCart(food)
        }
    }

    override fun getItemCount(): Int = foodList.size
}
