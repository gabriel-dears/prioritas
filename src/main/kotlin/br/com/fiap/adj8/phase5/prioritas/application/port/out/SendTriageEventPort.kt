package br.com.fiap.adj8.phase5.prioritas.application.port.out

// TODO: create common project for types and any other helper
// Add new type below
// use impl in the service
// check if message has been successfully sent
interface SendTriageEventPort {
    fun send(event: Any)
}