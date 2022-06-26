package com.geekbrains.listofemployees

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.geekbrains.listofemployees.ui.main.FragmentEmployers
import com.geekbrains.listofemployees.ui.room.FragmentRoomEmployers

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        filterConnection()
    }

    private fun filterConnection() {
        val filterConnection = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        registerReceiver(networkStateReceiver, filterConnection)
    }

    private var networkStateReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            val noConnectivity =
                intent.getBooleanExtra(ConnectivityManager.EXTRA_NO_CONNECTIVITY, false)
            if (!noConnectivity) {
                onConnectionYes()
            } else {
                onConnectionNo()
            }
        }
    }

    fun onConnectionNo() {
        supportFragmentManager.beginTransaction()
            .add(R.id.container_main_activity, FragmentRoomEmployers.newInstance())
            .commitNow()
        Toast.makeText(this, R.string.room_no_internet, Toast.LENGTH_LONG).show()
    }

    fun onConnectionYes() {
        supportFragmentManager.beginTransaction()
            .add(R.id.container_main_activity, FragmentEmployers.newInstance())
            .commitNow()
    }

}