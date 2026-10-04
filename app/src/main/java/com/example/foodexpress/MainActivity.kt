package com.example.foodexpress

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.foodexpress.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    companion object {
        const val DEMO_MOBILE = "9876543210"
        const val DEMO_PASSWORD = "1234"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loginButton.setOnClickListener {
            login()
        }
    }

    private fun login() {
        val name = binding.nameEditText.text.toString().trim()
        val mobile = binding.mobileEditText.text.toString().trim()
        val password = binding.passwordEditText.text.toString()

        if (name.isEmpty()) {
            binding.nameEditText.error = "Enter your name"
            return
        }

        if (mobile != DEMO_MOBILE || password != DEMO_PASSWORD) {
            Toast.makeText(
                this,
                "Invalid login. Demo: 9876543210 / 1234",
                Toast.LENGTH_LONG
            ).show()
            return
        }

        val intent = Intent(this, FoodListActivity::class.java)
        intent.putExtra("USER_NAME", name)
        startActivity(intent)
        finish()
    }
}
