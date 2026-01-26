package br.com.fiap.adj8.phase5.prioritas.domain.model

enum class RiskLevel(val colorName: String, val maxWaitTimeMinutes: Int) {
    EMERGENCY("Red", 0),
    VERY_URGENT("Orange", 10),
    URGENT("Yellow", 60),
    STANDARD("Green", 120),
    NON_URGENT("Blue", 240);
}