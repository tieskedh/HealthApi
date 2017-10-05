package nl.hanze.healthapi.data

import com.google.gson.Gson

/**
 * Created by Laurens on 28-9-2017.
 */
data class BloodPressure(
        val systolic: Double = 0.0,
        val diastolic: Double = 0.0
) : Measurement {
    override fun toJson() = GSON.toJson(this)
}