package br.com.fiap.adj8.phase5.prioritas.domain.rules.impl

import br.com.fiap.adj8.phase5.prioritas.domain.model.RiskLevel
import br.com.fiap.adj8.phase5.prioritas.domain.model.VitalSigns
import br.com.fiap.adj8.phase5.prioritas.domain.rules.TriageRule

class VeryUrgentRule : TriageRule {

    override fun matches(vitalSigns: VitalSigns): Boolean {
        val highFever = vitalSigns.temperature != null && vitalSigns.temperature >= 39.0
        val moderateHypoxia = vitalSigns.oxygenSaturation != null && vitalSigns.oxygenSaturation < 94
        val highHeartRate = vitalSigns.heartRate != null && vitalSigns.heartRate in 121..140
        return highFever || moderateHypoxia || highHeartRate
    }

    override fun getRiskLevel(): RiskLevel {
        return RiskLevel.VERY_URGENT
    }
}