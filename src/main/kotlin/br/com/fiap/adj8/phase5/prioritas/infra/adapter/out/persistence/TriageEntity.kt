package br.com.fiap.adj8.phase5.prioritas.infra.adapter.out.persistence

import br.com.fiap.adj8.phase5.prioritas.domain.model.RiskLevel
import jakarta.persistence.*
import java.time.LocalDateTime
import java.util.*

@Entity
@Table(name = "triages")
class TriageEntity(
    @Id
    val id: UUID,

    @Column(nullable = false)
    val patientId: UUID,

    @Enumerated(EnumType.STRING)
    val riskLevel: RiskLevel,

    val assessedAt: LocalDateTime,

    val temperature: Double?,
    val heartRate: Int?,
    val oxygenSaturation: Int?,
    val hasChestPain: Boolean,
    val systolicPressure: Int?,
    val diastolicPressure: Int?
) {
    constructor() : this(
        UUID.randomUUID(),
        UUID.randomUUID(),
        RiskLevel.STANDARD,
        LocalDateTime.now(),
        null,
        null,
        null,
        false,
        null,
        null
    )
}