package nl.hanze.healthapi.api

import android.bluetooth.BluetoothSocket
import nl.hanze.healthapi.data.Measurement
import nl.hanze.healthapi.data.MeasurementRequest
import nl.hanze.healthapi.data.MeasurementType

/**
 * Created by thijs on 5-10-2017.
 */
abstract class NaiveJsonRequestHandler(socket: BluetoothSocket) : JsonInteractor(socket){
    init {
        jsonDataInput.addOnArrival {
            val request = MeasurementRequest.fromJson(it)
            val measurement = generateMeasurementWithType(request.measurementType)
            jsonDataOutput.write(measurement.toJson())
        }
    }
    protected abstract fun generateMeasurementWithType(measurementType: MeasurementType): Measurement
}