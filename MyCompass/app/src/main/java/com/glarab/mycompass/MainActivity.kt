package com.glarab.mycompass

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), SensorEventListener {


    lateinit var manager: SensorManager
    lateinit var accelerometer: Sensor
    lateinit var magnetometer: Sensor
    val gravity = FloatArray(3)
    val magnetic_field = FloatArray(3)
    var azimuth = 0.0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        compassTest.update(45.0F)
        manager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        accelerometer = manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        magnetometer = manager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD)
    }

    fun startSensors() {
        manager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_GAME)
        manager.registerListener(this, magnetometer, SensorManager.SENSOR_DELAY_GAME)
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
    }

    override fun onSensorChanged(event: SensorEvent?) {

        val alpha = 0.97f

        synchronized(this, fun() {
            if (event?.sensor?.type == Sensor.TYPE_ACCELEROMETER) {
                gravity[0] = alpha * gravity[0] + (1 - alpha) * event!!.values[0]
                gravity[1] = alpha * gravity[1] + (1 - alpha) * event!!.values[1]
                gravity[2] = alpha * gravity[2] + (1 - alpha) * event!!.values[2]
            }

            if (event?.sensor?.type === Sensor.TYPE_MAGNETIC_FIELD) {
                magnetic_field[0] = alpha * magnetic_field[0] + (1 - alpha) * event!!.values[0]
                magnetic_field[1] = alpha * magnetic_field[1] + (1 - alpha) * event!!.values[1]
                magnetic_field[2] = alpha * magnetic_field[2] + (1 - alpha) * event!!.values[2]
            }

            val R = FloatArray(9)
            val I = FloatArray(9)
            val success = SensorManager.getRotationMatrix(R, I, gravity, magnetic_field)
            if (success) {
                val orientation = FloatArray(3)
                SensorManager.getOrientation(R, orientation)
                azimuth = Math.toDegrees(orientation[0].toDouble()).toFloat() // orientation
                azimuth = (azimuth + 360) % 360
                compassTest.update(azimuth)
            }
        })
    }

    override fun onPause() {
        super.onPause()
        manager.unregisterListener(this)
    }

    override fun onResume() {
        super.onResume()
        startSensors()
    }
}
