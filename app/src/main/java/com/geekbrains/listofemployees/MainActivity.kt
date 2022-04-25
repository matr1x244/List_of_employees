package com.geekbrains.listofemployees

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.geekbrains.listofemployees.ui.FragmentEmployers

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container_main_activity, FragmentEmployers.newInstance())
                .commitNow()
        }
    }
}