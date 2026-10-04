package com.example.foodexpress

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodexpress.adapter.FoodAdapter
import com.example.foodexpress.databinding.ActivityFoodListBinding

class FoodListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFoodListBinding
    private lateinit var adapter: FoodAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFoodListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val userName = intent.getStringExtra("USER_NAME") ?: "Customer"
        binding.welcomeText.text = "Welcome, $userName"

        adapter = FoodAdapter(FoodData.items) { food ->
            CartManager.add(food)
            updateCartButton()
            Toast.makeText(this, "${food.name} added to cart", Toast.LENGTH_SHORT).show()
        }

        binding.foodRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.foodRecyclerView.adapter = adapter

        binding.cartButton.setOnClickListener {
            startActivity(Intent(this, CartActivity::class.java))
        }

        binding.feedbackButton.setOnClickListener {
            startActivity(Intent(this, FeedbackActivity::class.java))
        }

        binding.logoutButton.setOnClickListener {
            showLogoutDialog()
        }

        updateCartButton()
    }

    override fun onResume() {
        super.onResume()
        if (::binding.isInitialized) {
            updateCartButton()
        }
    }

    private fun updateCartButton() {
        binding.cartButton.text = "🛒 Cart (${CartManager.getItemCount()})"
    }

    private fun showLogoutDialog() {
        AlertDialog.Builder(this)
            .setTitle("Logout")
            .setMessage("Are you sure you want to logout?")
            .setNegativeButton("Cancel", null)
            .setPositiveButton("Logout") { _, _ ->
                CartManager.clear()
                val intent = Intent(this, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
                finish()
            }
            .show()
    }
}
