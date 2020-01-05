package com.glarab.shakeaction

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.media.MediaPlayer
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.glarab.shakeaction.service.ShakeService

class MainActivity : AppCompatActivity() {

    private lateinit var myReceiver: MyShakeReceiver
    private var mp: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initMediaPlayer()
        initService()
        initReceiver()

    }

    private fun initMediaPlayer() {
        mp = MediaPlayer.create(this, R.raw.latigo)
    }

    private fun initReceiver() {
        myReceiver = MyShakeReceiver()
        val intentFilter = IntentFilter()
        intentFilter.addAction(ShakeService.SHAKE_ACTION)
        LocalBroadcastManager.getInstance(this).registerReceiver(
            myReceiver, intentFilter
        )
    }

    private fun initService() {
        val intent = Intent(this, ShakeService::class.java)
        startService(intent)
    }

    fun startSound() {
        if ((mp!!.isPlaying).not()) {
            mp!!.start()
        }
    }

    private inner class MyShakeReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            val action = intent.action
            if (action == ShakeService.SHAKE_ACTION) {
                startSound()
            }
        }
    }
}
