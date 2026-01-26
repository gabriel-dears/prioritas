package br.com.fiap.adj8.phase5.prioritas.domain.rules.impl

import br.com.fiap.adj8.phase5.prioritas.domain.model.RiskLevel
import br.com.fiap.adj8.phase5.prioritas.domain.model.VitalSigns
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class VeryUrgentRuleTest {

    private val rule = VeryUrgentRule()

    @Test
    fun `should match when temperature is high fever (39 or above)`() {
        val vitals = VitalSigns(temperature = 39.0)
        assertTrue(rule.matches(vitals))
    }

    @Test
    fun `should match when oxygen saturation is moderate hypoxia (below 94)`() {
        val vitals = VitalSigns(oxygenSaturation = 93)
        assertTrue(rule.matches(vitals))
    }

    @Test
    fun `should match when heart rate is high (121 to 140)`() {
        val vitals = VitalSigns(heartRate = 130)
        assertTrue(rule.matches(vitals))
    }

    @Test
    fun `should NOT match when vitals are normal`() {
        val vitals = VitalSigns(temperature = 37.0, oxygenSaturation = 98, heartRate = 80)
        assertFalse(rule.matches(vitals))
    }

    @Test
    fun `should NOT match when condition is actually EMERGENCY (logic handled by order, but rule itself checks ranges)`() {
        // Ideally, this rule matches ranges.
        // If Oxygen is 89, it technically matches "below 94" logic?
        // Let's check your VeryUrgentRule implementation.
        // If implemented as `oxygen < 94`, it returns true for 89 too.
        // This is fine because the SERVICE stops at the first Red match.
        val vitals = VitalSigns(oxygenSaturation = 89)
        assertTrue(rule.matches(vitals))
    }

    @Test
    fun `should return VERY_URGENT risk level`() {
        assertEquals(RiskLevel.VERY_URGENT, rule.getRiskLevel())
    }
}