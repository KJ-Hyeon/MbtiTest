package com.example.mbtitest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mbtitest.databinding.ActivityResultBinding
import java.util.Locale

class ResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val result = intent.getIntegerArrayListExtra("result") ?: arrayListOf<Int>()
        val resultType = listOf(
            listOf("E", "I"),
            listOf("N", "S"),
            listOf("T", "F"),
            listOf("J", "P")
        )

        var resultString = ""
        for (i in result.indices) {
            resultString += resultType[i][result[i] - 1]
        }

        binding.tvResultValue.text = resultString
        val imageRes = resources.getIdentifier(
            "ic_${resultString.toLowerCase(Locale.ROOT)}",
            "drawable",
            packageName
        )
        binding.ivResultImage.setImageResource(imageRes)
        binding.btnRetry.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }
    }
}