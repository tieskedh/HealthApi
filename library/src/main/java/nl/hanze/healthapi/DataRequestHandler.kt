package nl.hanze.healthapi

import nl.hanze.healthapi.IO.input.Consumer
import nl.hanze.healthapi.data.Measurement
import nl.hanze.healthapi.data.MeasurementRequest
import nl.hanze.healthapi.data.MeasurementType
import nl.hanze.json.data.SerializedJsonMeasurement

/**
 * Created by Laurens on 28-9-2017.
 */
abstract class DataRequestHandler(private val answerSink: Consumer<SerializedJsonMeasurement>) : Consumer<MeasurementRequest> {

    protected abstract fun generateMeasurementWithType(measurementType: MeasurementType?): Measurement

    fun accept(measurementRequest: MeasurementRequest) {
        val measurement = generateMeasurementWithType(measurementRequest.measurementType)
        val serializedMeasurement = SerializedJsonMeasurement(measurementRequest.measurementType, measurement.toJson())
        answerSink(serializedMeasurement)
    }
}
