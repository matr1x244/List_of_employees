package com.geekbrains.listofemployees

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.geekbrains.listofemployees.ui.main.FragmentEmployers

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.container_main_activity, FragmentEmployers.newInstance())
                .commitNow()
        }
    }
}