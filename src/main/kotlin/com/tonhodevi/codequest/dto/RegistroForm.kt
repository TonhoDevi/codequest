package com.tonhodevi.codequest.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class RegistroForm(
    @field:NotBlank(message = "Informe seu nome")
    var nome: String = "",

    @field:NotBlank(message = "Informe seu e-mail")
    @field:Email(message = "E-mail inválido")
    var email: String = "",

    @field:NotBlank(message = "Informe uma senha")
    @field:Size(min = 6, message = "A senha deve ter no mínimo 6 caracteres")
    var senha: String = "",

    var confirmarSenha: String = ""
)