package br.com.fiap.adj8.phase5.prioritas.domain.rules.impl

import br.com.fiap.adj8.phase5.prioritas.domain.model.RiskLevel
import br.com.fiap.adj8.phase5.prioritas.domain.model.VitalSigns
import br.com.fiap.adj8.phase5.prioritas.domain.rules.TriageRule

class UrgentRule : TriageRule {

    override fun matches(vitalSigns: VitalSigns): Boolean {
        val fever = vitalSigns.temperature != null && vitalSigns.temperature in 37.8..38.9
        val elevatedHeartRate = vitalSigns.heartRate != null && vitalSigns.heartRate in 101..120

        return fever || elevatedHeartRate
    }

    override fun getRiskLevel(): RiskLevel {
        return RiskLevel.URGENT
    }
}