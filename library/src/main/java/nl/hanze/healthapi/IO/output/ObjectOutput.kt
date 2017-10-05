package nl.hanze.healthapi.IO.output

import nl.hanze.healthapi.JsonDataOutput
import nl.hanze.healthapi.data.GSON

/**
 * Created by Laurens on 28-9-2017.
 */
class ObjectOutput(private val jsonDataOutput: JsonDataOutput) : AutoCloseable {

    fun write(value: Any) {
        val serializedObject = GSON.toJson(value)
        jsonDataOutput.write(serializedObject)
    }

    @Throws(Exception::class)
    override fun close() = jsonDataOutput.close()
}
