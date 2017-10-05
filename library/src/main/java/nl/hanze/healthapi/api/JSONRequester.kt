package nl.hanze.healthapi.api

import android.bluetooth.BluetoothSocket
import nl.hanze.healthapi.IO.input.Consumer
import nl.hanze.healthapi.data.MeasurementRequest
import nl.hanze.json.data.SerializedJsonMeasurement

/**
 * Requester, where the programmer needs to take in account that they should ask one thing at a time.
 */
open class NaiveJsonRequester(private val socket: BluetoothSocket) : JsonInteractor(socket) {
    open fun doRequest(measurementRequest: MeasurementRequest, callback: Consumer<SerializedJsonMeasurement>) {
        jsonDataInput.addOnArrival {
            val measurement = SerializedJsonMeasurement(measurementRequest.measurementType, it)
            callback(measurement)
        }
        jsonDataOutput.write(measurementRequest.toJSON())
    }

    open fun waitRequest(measurementRequest: MeasurementRequest): SerializedJsonMeasurement {
        var returnType: SerializedJsonMeasurement? = null
        doRequest(measurementRequest) {
            returnType = it
        }
        while (returnType == null) {
            /* wait untill returntype contains something */
        }
        return returnType!!
    }

    override fun close() {
        jsonDataOutput.close()
        jsonDataInput.close()
        socket.close()
    }
}