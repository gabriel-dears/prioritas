package br.com.fiap.adj8.phase5.prioritas.integration

import br.com.fiap.adj8.phase5.prioritas.AbstractIntegrationTest
import br.com.fiap.adj8.phase5.prioritas.domain.model.RiskLevel
import br.com.fiap.adj8.phase5.prioritas.infra.adapter.out.persistence.SpringDataTriageRepository
import com.fasterxml.jackson.databind.ObjectMapper
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.util.*

class TriageIntegrationTest : AbstractIntegrationTest() {
    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var repository: SpringDataTriageRepository

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @BeforeEach
    fun setup() {
        repository.deleteAll()
    }

    @Test
    fun `should perform triage and save result as EMERGENCY when symptoms match`() {
        // 1. Arrange
        val patientId = UUID.randomUUID()
        val requestBody = mapOf(
            "patientId" to patientId,
            "hasChestPain" to true, // Isso deve gerar EMERGENCY
            "heartRate" to 110,
            "oxygenSaturation" to 98
        )

        // 2. Act
        mockMvc.perform(
            post("/api/triages")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestBody))
        )
            // 3. Assert (HTTP)
            .andExpect(status().isCreated)
            .andExpect(jsonPath("$.riskLevel").value("EMERGENCY"))
            .andExpect(jsonPath("$.riskColor").value("Red"))

        // 4. Assert (Database)
        val savedTriages = repository.findAll()
        assertThat(savedTriages).hasSize(1)
        assertThat(savedTriages[0].riskLevel).isEqualTo(RiskLevel.EMERGENCY)
    }
}