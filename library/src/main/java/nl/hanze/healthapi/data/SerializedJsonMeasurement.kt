package nl.hanze.json.data

import nl.hanze.healthapi.data.MeasurementType

/**
 * Created by Laurens on 28-9-2017.
 */
data class SerializedJsonMeasurement(var measurementType: MeasurementType?, var serializedJson: String?)
