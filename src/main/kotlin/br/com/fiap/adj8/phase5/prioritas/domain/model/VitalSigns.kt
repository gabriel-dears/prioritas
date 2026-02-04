package br.com.fiap.adj8.phase5.prioritas.domain.model

data class VitalSigns(
    val temperature: Double? = null,
    val heartRate: Int? = null,        // bpm
    val oxygenSaturation: Int? = null, // % (0-100)
    val hasChestPain: Boolean = false, // Defaults to false
    val systolicPressure: Int? = null,
    val diastolicPressure: Int? = null
) {
    init {
        temperature?.let {
            require(it in 25.0..46.0) { "Temperature must be between 25.0 and 46.0" }
        }
        oxygenSaturation?.let {
            require(it in 0..100) { "Oxygen saturation must be between 0 and 100" }
        }
        heartRate?.let {
            require(it > 0) { "Heart rate must be positive" }
        }
    }
}