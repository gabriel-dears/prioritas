package br.com.fiap.adj8.phase5.prioritas.infra.adapter.out.persistence

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface SpringDataTriageRepository : JpaRepository<TriageEntity, UUID>