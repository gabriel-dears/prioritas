package br.com.fiap.adj8.phase5.prioritas.domain.rules.impl

import br.com.fiap.adj8.phase5.prioritas.domain.model.RiskLevel
import br.com.fiap.adj8.phase5.prioritas.domain.model.VitalSigns
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class EmergencyRuleTest {

    private val rule = EmergencyRule()

    @Test
    fun `should match when patient has chest pain`() {
        val vitals = VitalSigns(hasChestPain = true)
        assertTrue(rule.matches(vitals), "Should match chest pain")
    }

    @Test
    fun `should match when oxygen saturation is critical (below 90)`() {
        val vitals = VitalSigns(oxygenSaturation = 89)
        assertTrue(rule.matches(vitals), "Should match critical hypoxia")
    }

    @Test
    fun `should match when heart rate is extreme (above 140)`() {
        val vitals = VitalSigns(heartRate = 141)
        assertTrue(rule.matches(vitals), "Should match extreme tachycardia")
    }

    @Test
    fun `should NOT match when vitals are normal`() {
        val vitals = VitalSigns(
            oxygenSaturation = 98,
            heartRate = 80,
            hasChestPain = false
        )
        assertFalse(rule.matches(vitals))
    }

    @Test
    fun `should return EMERGENCY risk level`() {
        assertEquals(RiskLevel.EMERGENCY, rule.getRiskLevel())
    }
}