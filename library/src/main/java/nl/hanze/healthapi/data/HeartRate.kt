package nl.hanze.healthapi.data

/**
 * Created by Laurens on 28-9-2017.
 */
data class HeartRate(val value: Double = 0.0) : Measurement {
    override fun toJson() = GSON.toJson(this)
}
