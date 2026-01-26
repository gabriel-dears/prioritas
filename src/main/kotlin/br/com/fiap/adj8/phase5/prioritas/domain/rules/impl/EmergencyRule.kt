package br.com.fiap.adj8.phase5.prioritas.domain.rules.impl

import br.com.fiap.adj8.phase5.prioritas.domain.model.RiskLevel
import br.com.fiap.adj8.phase5.prioritas.domain.model.VitalSigns
import br.com.fiap.adj8.phase5.prioritas.domain.rules.TriageRule

class EmergencyRule : TriageRule {

    override fun matches(vitalSigns: VitalSigns): Boolean {
        val lowOxygen = vitalSigns.oxygenSaturation != null && vitalSigns.oxygenSaturation < 90
        val extremeHeartRate = vitalSigns.heartRate != null && vitalSigns.heartRate > 140
        val chestPain = vitalSigns.hasChestPain

        return lowOxygen || extremeHeartRate || chestPain
    }

    override fun getRiskLevel(): RiskLevel {
        return RiskLevel.EMERGENCY
    }
}