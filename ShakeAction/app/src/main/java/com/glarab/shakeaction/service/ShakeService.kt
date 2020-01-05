package com.glarab.shakeaction.service

import android.app.Service
import android.content.Context
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Handler
import android.os.IBinder
import androidx.localbroadcastmanager.content.LocalBroadcastManager

class ShakeService : Service(), SensorEventListener {

    private var mSensorManager: SensorManager? = null
    private var mAccelerometer: Sensor? = null
    private var mAccel: Float = 0F
    private var mAccelCurrent: Float = 0F
    private var mAccelLast: Float = 0F

    override fun onBind(p0: Intent): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        mSensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        mAccelerometer = mSensorManager!!
            .getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        mSensorManager!!.registerListener(
            this, mAccelerometer,
            SensorManager.SENSOR_DELAY_UI, Handler()
        )
        return START_STICKY
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {

    }

    override fun onSensorChanged(event: SensorEvent) {
        //REFERENCE:https://stackoverflow.com/questions/2317428/how-to-refresh-app-upon-shaking-the-device
        val x = event.values[0]
        val y = event.values[1]
        val z = event.values[2]
        mAccelLast = mAccelCurrent
        mAccelCurrent = Math.sqrt((x * x + y * y + z * z).toDouble()).toFloat()
        val delta = mAccelCurrent - mAccelLast
        mAccel = mAccel * 0.9f + delta

        if (mAccel > 11) {
            val intent = Intent(Companion.SHAKE_ACTION)
            LocalBroadcastManager.getInstance(this).sendBroadcast(intent)
        }
    }


    companion object {
        const val SHAKE_ACTION = "SHAKE_ACTION"
    }
}