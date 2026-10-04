package com.example.foodexpress

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.foodexpress.databinding.ActivityFeedbackBinding

class FeedbackActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFeedbackBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFeedbackBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backButton.setOnClickListener {
            finish()
        }

        loadSavedReview()

        binding.submitReviewButton.setOnClickListener {
            submitReview()
        }
    }

    private fun submitReview() {
        val rating = binding.ratingBar.rating
        val review = binding.reviewEditText.text.toString().trim()

        if (rating == 0f) {
            Toast.makeText(this, "Please select a rating", Toast.LENGTH_SHORT).show()
            return
        }

        if (review.isEmpty()) {
            binding.reviewEditText.error = "Please enter your review"
            return
        }

        getSharedPreferences("FoodExpressReviews", Context.MODE_PRIVATE)
            .edit()
            .putFloat("rating", rating)
            .putString("review", review)
            .apply()

        Toast.makeText(this, "Thank you! Your review was submitted.", Toast.LENGTH_LONG).show()
        showMyReview(rating, review)
        binding.reviewEditText.text?.clear()
        binding.ratingBar.rating = 0f
    }

    private fun loadSavedReview() {
        val prefs = getSharedPreferences("FoodExpressReviews", Context.MODE_PRIVATE)
        if (prefs.contains("review")) {
            showMyReview(
                prefs.getFloat("rating", 5f),
                prefs.getString("review", "") ?: ""
            )
        }
    }

    private fun showMyReview(rating: Float, review: String) {
        binding.myReviewCard.visibility = View.VISIBLE
        binding.myRatingText.text = "⭐ ${rating.toInt()}/5"
        binding.myReviewText.text = review
    }
}
