package nl.hanze.healthapi.data

/**
 * Created by Laurens on 28-9-2017.
 */
class MeasurementRequest(var measurementType: MeasurementType) {
    companion object {
        fun fromJson(json: String) = GSON.fromJson(json, MeasurementRequest::class.java)
    }
    fun toJSON() = GSON.toJson(this)
}
