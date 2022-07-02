package com.geekbrains.listofemployees

import android.animation.ObjectAnimator
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.animation.AnticipateInterpolator
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.geekbrains.listofemployees.ui.main.FragmentEmployers
import com.geekbrains.listofemployees.ui.room.FragmentRoomEmployers

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startSplash()
        setContentView(R.layout.activity_main)
        filterConnection()
    }

    private fun startSplash() {
        val version = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S
        if (version) {
            val screen = installSplashScreen()
            screen.setOnExitAnimationListener { screenProvider ->
                ObjectAnimator.ofFloat(
                    screenProvider.view,
                    View.TRANSLATION_X,
                    0f,
                    screenProvider.view.height.toFloat()
                ).apply {
                    duration = 1000
                    interpolator = AnticipateInterpolator()
                    doOnEnd {
                        screenProvider.remove()
                    }
                }.start()
            }
        }
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

    override fun onDestroy() {
        unregisterReceiver(networkStateReceiver)
        super.onDestroy()
    }
}