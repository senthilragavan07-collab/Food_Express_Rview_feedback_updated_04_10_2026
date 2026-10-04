package com.example.foodexpress

import com.example.foodexpress.model.CartItem
import com.example.foodexpress.model.FoodItem

object CartManager {

    private val items = mutableListOf<CartItem>()

    fun add(food: FoodItem) {
        val existing = items.find { it.food.id == food.id }
        if (existing != null) {
            existing.quantity++
        } else {
            items.add(CartItem(food, 1))
        }
    }

    fun increase(foodId: Int) {
        items.find { it.food.id == foodId }?.let { it.quantity++ }
    }

    fun decrease(foodId: Int) {
        val item = items.find { it.food.id == foodId } ?: return
        item.quantity--
        if (item.quantity <= 0) {
            items.remove(item)
        }
    }

    fun remove(foodId: Int) {
        items.removeAll { it.food.id == foodId }
    }

    fun getItems(): List<CartItem> = items.toList()

    fun getSubtotal(): Int =
        items.sumOf { it.food.price * it.quantity }

    fun getItemCount(): Int =
        items.sumOf { it.quantity }

    fun clear() {
        items.clear()
    }
}
