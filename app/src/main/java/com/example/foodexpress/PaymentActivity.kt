package com.example.foodexpress

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.foodexpress.databinding.ActivityPaymentBinding

class PaymentActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPaymentBinding

    private val deliveryCharge = 40

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPaymentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val subtotal = CartManager.getSubtotal()
        val total = subtotal + if (subtotal > 0) deliveryCharge else 0

        binding.amountText.text = "₹$total"

        binding.backButton.setOnClickListener {
            finish()
        }

        binding.payButton.setOnClickListener {
            processPayment(total)
        }
    }

    private fun processPayment(total: Int) {
        if (binding.upiRadio.isChecked) {
            val upi = binding.upiEditText.text.toString().trim()
            if (upi.isEmpty()) {
                binding.upiEditText.error = "Enter demo UPI ID"
                return
            }
        }

        if (binding.cardRadio.isChecked) {
            val card = binding.cardEditText.text.toString().trim()
            if (card.length < 4) {
                binding.cardEditText.error = "Enter demo card number"
                return
            }
        }

        Toast.makeText(this, "Demo payment successful", Toast.LENGTH_SHORT).show()

        val intent = Intent(this, OrderSuccessActivity::class.java)
        intent.putExtra("TOTAL_AMOUNT", total)
        startActivity(intent)
        finish()
    }
}
