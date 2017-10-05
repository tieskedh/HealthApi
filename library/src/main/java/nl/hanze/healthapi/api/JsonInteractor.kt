package nl.hanze.healthapi.api

import android.bluetooth.BluetoothSocket
import nl.hanze.healthapi.IO.output.DecoratingJsonDataOutput
import nl.hanze.healthapi.JsonDataOutput
import nl.hanze.json.IO.input.JsonDataInput
import java.io.Closeable

/**
 * Created by thijs on 5-10-2017.
 */
open class JsonInteractor(private val socket: BluetoothSocket) : Closeable{
    protected val jsonDataInput: JsonDataInput = JsonDataInput(socket.inputStream)
    protected val jsonDataOutput: JsonDataOutput = DecoratingJsonDataOutput(socket.outputStream)

    override fun close() {
        socket.close()
        jsonDataInput.close()
        jsonDataOutput.close()
    }
}