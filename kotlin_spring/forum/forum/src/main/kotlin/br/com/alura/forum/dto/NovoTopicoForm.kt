package br.com.alura.forum.dto

import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

// DTO => Classe que representa os atributos que estão chegando do cliente para a API. Comunicação entre camadas

data class NovoTopicoForm (

    @field:NotEmpty(message = "Título nao pode ser em branco") @Size(min = 5, max = 100, message = "Titulo deve ter entre 5 e 100 caracteres") val titulo : String,
    @field:NotEmpty(message = "Mensagem nao pode ser em branco") val mensagem: String,
    @field:NotNull val idCurso: Long,
    @field:NotNull val idAutor: Long
)
