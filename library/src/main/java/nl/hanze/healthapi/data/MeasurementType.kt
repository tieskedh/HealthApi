package nl.hanze.healthapi.data

/**
 * Created by Laurens on 3-10-2017.
 */
enum class MeasurementType {
    BLOOD_PRESSURE,
    ECG_WAVES,
    HEART_RATE
}
inline fun <reified T :Measurement> getType() = when(T::class){
    BloodPressure::class -> MeasurementType.BLOOD_PRESSURE
    ECGWaves::class -> MeasurementType.ECG_WAVES
    HeartRate::class -> MeasurementType.HEART_RATE
    else -> throw IllegalArgumentException("not a valid class")
}