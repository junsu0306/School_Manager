package com.example.myapplication
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_calculator_2) // calculator_2.xml 파일을 설정

        replaceFragment(calculator_2())
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager: FragmentManager = supportFragmentManager
        val transaction: FragmentTransaction = fragmentManager.beginTransaction()

        // 프래그먼트를 fragment_container 레이아웃에 추가 또는 교체
        transaction.replace(R.id.frameLayout, fragment)

        // 이전 프래그먼트를 백 스택에 추가하려면 다음과 같이 사용할 수 있습니다.
        // transaction.addToBackStack(null)

        transaction.commit()
    }
}
