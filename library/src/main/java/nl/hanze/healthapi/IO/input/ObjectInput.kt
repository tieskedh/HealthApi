package nl.hanze.healthapi.IO.input


import nl.hanze.healthapi.JsonInputListener
import java.util.*

/**
 * Created by Laurens on 28-9-2017.
 */

typealias Consumer<T> = (T)->Unit
typealias Deserializer<T> = (String) -> T
class ObjectInput<T>(private val deserializer: Deserializer<T>) : JsonInputListener {
    private val consumers = ArrayList<Consumer<T>>()

    fun addConsumer(consumer: Consumer<T>) {
        consumers.add(consumer)
    }

    fun deleteConsumer(consumer: Consumer<T>) {
        consumers.remove(consumer)
    }

    override fun onArrival(json: String) {
        val deserializedObject = deserializer(json)
        consumers.forEach { consumer -> consumer(deserializedObject) }
    }
}
