package nl.hanze.healthapi.data

import java.util.*

/**
 * Created by Laurens on 28-9-2017.
 */
class ECGWaves : Measurement {
    private val _measurements = ArrayList<ECGMeasurement>()
    val measurements get() = _measurements.toList()
    fun addMeasurement(measurement: ECGMeasurement) = _measurements.add(measurement)
    override fun toJson() = GSON.toJson(measurements)
}

data class ECGMeasurement(
        var time: Long = System.currentTimeMillis(),
        var amplitude: Double = 0.0
)