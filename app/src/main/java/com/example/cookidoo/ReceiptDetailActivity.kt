package com.example.cookidoo

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.content.res.ColorStateList
import androidx.core.content.ContextCompat
import androidx.core.widget.ImageViewCompat
import androidx.appcompat.app.AppCompatActivity
import com.example.cookidoo.ui.explore.destaque.MockData
import androidx.annotation.ColorRes

class ReceiptDetailActivity : AppCompatActivity() {

    private lateinit var receipt : MockData.Receipt;

    private fun ImageView.setTint(@ColorRes colorRes: Int) {
        ImageViewCompat.setImageTintList(this, ColorStateList.valueOf(ContextCompat.getColor(context, colorRes)))
    }

    private fun fillData() {
        val starIcons = kotlin.arrayOf(
            findViewById<ImageView>(R.id.star1),
            findViewById<ImageView>(R.id.star2),
            findViewById<ImageView>(R.id.star3),
            findViewById<ImageView>(R.id.star4),
            findViewById<ImageView>(R.id.star5),
        )

        for (i in 0..(receipt.rating - 1)) {
            starIcons[i].setTint(R.color.green_third)
        }

        findViewById<ImageView>(R.id.receiptDetailImage).setImageResource(receipt.image);
        findViewById<TextView>(R.id.receiptName).setText(receipt.name);
        findViewById<TextView>(R.id.ratingLabel).setText(receipt.labeledRating)
        findViewById<TextView>(R.id.ratingCountLabel).setText(receipt.labeledRatingCount)
        findViewById<TextView>(R.id.dificultyTextView).setText(receipt.dificulty)
        findViewById<TextView>(R.id.prepTime).setText(receipt.prepTime)
        findViewById<TextView>(R.id.totalTime).setText(receipt.totalTime)
        findViewById<TextView>(R.id.portions).setText(receipt.portions)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        window.decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receipt_detail2);
        receipt = MockData.getReceipts().get(intent.getIntExtra("receiptPosition", -1));

        fillData();

    }
}