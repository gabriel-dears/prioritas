package br.com.fiap.adj8.phase5.prioritas.domain.rules

import br.com.fiap.adj8.phase5.prioritas.domain.model.RiskLevel
import br.com.fiap.adj8.phase5.prioritas.domain.model.VitalSigns

interface TriageRule {
    /**
     * Checks if the vital signs match this specific rule criteria.
     */
    fun matches(vitalSigns: VitalSigns): Boolean

    /**
     * Returns the Risk Level associated with this rule.
     */
    fun getRiskLevel(): RiskLevel
}