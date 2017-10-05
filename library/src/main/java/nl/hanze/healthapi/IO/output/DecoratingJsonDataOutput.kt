package nl.hanze.healthapi.IO.output

import nl.hanze.healthapi.JsonDataOutput
import java.io.OutputStream
import java.io.PrintWriter

/**
 * Created by Laurens on 28-9-2017.
 */
class DecoratingJsonDataOutput(val printWriter: PrintWriter) : JsonDataOutput {
    constructor(outputStream: OutputStream) : this(PrintWriter(outputStream))

    override fun write(json: String) {
        printWriter.println(json)
        printWriter.flush()
    }

    @Throws(Exception::class)
    override fun close() = printWriter.close()
}
