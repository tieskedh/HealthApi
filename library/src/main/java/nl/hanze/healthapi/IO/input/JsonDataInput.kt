package nl.hanze.json.IO.input

import com.google.gson.JsonStreamParser
import nl.hanze.healthapi.JsonInputListener
import java.io.InputStream
import java.io.InputStreamReader
import java.util.*
import java.util.concurrent.Executors
import java.util.function.Consumer

/**
 * Created by Laurens on 28-9-2017.
 */
class JsonDataInput(private val inputStream: InputStream) : Runnable, AutoCloseable {

    private val jsonStreamParser = JsonStreamParser(InputStreamReader(inputStream))
    private var running = true
    private val jsonInputListeners = ArrayList<JsonInputListener>()
    private val executorService = Executors.newSingleThreadExecutor()

    fun addJsonInputListener(inputListener: JsonInputListener) {
        jsonInputListeners += inputListener
    }

    fun addOnArrival(listener: (String) -> Unit) {
        jsonInputListeners += JsonInputListener(listener)
    }

    fun removeOnArrival(listener: (String) -> Unit) {
        jsonInputListeners -= JsonInputListener(listener)
    }

    fun removeJsonInputListener(inputListener: JsonInputListener) {
        jsonInputListeners -= inputListener
    }

    private fun pushJsonToListeners(json: String) = jsonInputListeners.asSequence().map { it ->
        Runnable { it.onArrival(json) }
    }.toList()

    override fun run() {
        while (running) {
            val nextJsonObject = jsonStreamParser.next()
            pushJsonToListeners(nextJsonObject.toString()).forEach {
                Consumer<Runnable> {
                    executorService.submit(it)
                }
            }
        }
        inputStream.use {}
    }

    @Throws(Exception::class)
    override fun close() {
        running = false
        executorService.shutdown()
    }
}
