package com.example.foodexpress

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.foodexpress.databinding.ActivityOrderSuccessBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlin.random.Random

class OrderSuccessActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOrderSuccessBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityOrderSuccessBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val total = intent.getIntExtra("TOTAL_AMOUNT", 0)
        val orderId = "FD${SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault()).format(Date())}${Random.nextInt(10, 99)}"

        binding.orderIdText.text = "Order ID: $orderId"
        binding.amountText.text = "Amount Paid: ₹$total"

        CartManager.clear()

        binding.homeButton.setOnClickListener {
            val intent = Intent(this, FoodListActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            startActivity(intent)
            finish()
        }
    }
}
