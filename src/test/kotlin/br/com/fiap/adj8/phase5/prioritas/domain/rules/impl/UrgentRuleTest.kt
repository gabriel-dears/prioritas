package br.com.fiap.adj8.phase5.prioritas.domain.rules.impl

import br.com.fiap.adj8.phase5.prioritas.domain.model.RiskLevel
import br.com.fiap.adj8.phase5.prioritas.domain.model.VitalSigns
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class UrgentRuleTest {

    private val rule = UrgentRule()

    @Test
    fun `should match when temperature is mild fever (37,8 to 38,9)`() {
        val vitals = VitalSigns(temperature = 38.0)
        assertTrue(rule.matches(vitals))
    }

    @Test
    fun `should match when heart rate is elevated (101 to 120)`() {
        val vitals = VitalSigns(heartRate = 110)
        assertTrue(rule.matches(vitals))
    }

    @Test
    fun `should NOT match when temperature is normal`() {
        val vitals = VitalSigns(temperature = 37.0)
        assertFalse(rule.matches(vitals))
    }

    @Test
    fun `should NOT match when temperature is high fever (matches VeryUrgent instead)`() {
        // Based on implementation: temperature in 37.8..38.9
        val vitals = VitalSigns(temperature = 39.5)
        assertFalse(rule.matches(vitals), "Should not match high fever")
    }

    @Test
    fun `should return URGENT risk level`() {
        assertEquals(RiskLevel.URGENT, rule.getRiskLevel())
    }
}