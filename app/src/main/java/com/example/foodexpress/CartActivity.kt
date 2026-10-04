package com.example.foodexpress

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.foodexpress.adapter.CartAdapter
import com.example.foodexpress.databinding.ActivityCartBinding

class CartActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCartBinding
    private lateinit var adapter: CartAdapter

    private val deliveryCharge = 40

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backButton.setOnClickListener {
            finish()
        }

        adapter = CartAdapter(CartManager.getItems()) {
            updateSummary()
        }

        binding.cartRecyclerView.adapter = adapter

        binding.checkoutButton.setOnClickListener {
            val subtotal = CartManager.getSubtotal()
            if (subtotal > 0) {
                startActivity(Intent(this, PaymentActivity::class.java))
            }
        }

        updateSummary()
    }

    private fun updateSummary() {
        val subtotal = CartManager.getSubtotal()
        val delivery = if (subtotal > 0) deliveryCharge else 0
        val total = subtotal + delivery

        binding.subtotalText.text = "Subtotal: ₹$subtotal"
        binding.deliveryText.text = "Delivery: ₹$delivery"
        binding.totalText.text = "Total: ₹$total"

        val empty = CartManager.getItems().isEmpty()
        binding.emptyText.visibility = if (empty) View.VISIBLE else View.GONE
        binding.checkoutButton.isEnabled = !empty
    }
}
