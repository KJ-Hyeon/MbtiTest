package com.example.mbtitest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.example.mbtitest.databinding.ActivityTestBinding

class TestActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTestBinding
    private lateinit var viewPager: ViewPager2

    val questionResults = QuestionResults()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTestBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewPager = binding.viewPager2
        viewPager.adapter = ViewPagerAdapter(this)
        // 화면을 사용자가 스와이프를 통해서 자유롭게 움직이는 동작을 막기 위해서
        viewPager.isUserInputEnabled = false
    }

    fun moveToNextQuestion() {
        if (viewPager.currentItem == 3) {
            val intent = Intent(this, ResultActivity::class.java)
            intent.putIntegerArrayListExtra("result", ArrayList(questionResults.results))
            startActivity(intent)

        } else {
            val nextItem = viewPager.currentItem + 1
            if (nextItem < (viewPager.adapter?.itemCount ?: 0)) {
                viewPager.setCurrentItem(nextItem, true)
            }
        }
    }


}

// 질문의 답변을 저장해놓기 위한 클래스
class QuestionResults {
    val results = mutableListOf<Int>()

    fun addResponses(response: List<Int>) { // 1,1,2
        // 같은 숫자끼리 Group을 지어서 카운트
        val mostFrequent = response.groupingBy { it }.eachCount().maxByOrNull { it.value }?.key
        mostFrequent?.let { results.add(it) }
    }

}