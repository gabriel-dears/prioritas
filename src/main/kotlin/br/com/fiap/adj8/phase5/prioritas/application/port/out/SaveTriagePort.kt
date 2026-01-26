package br.com.fiap.adj8.phase5.prioritas.application.port.out

import br.com.fiap.adj8.phase5.prioritas.domain.model.Triage

interface SaveTriagePort {
    fun save(triage: Triage): Triage
}