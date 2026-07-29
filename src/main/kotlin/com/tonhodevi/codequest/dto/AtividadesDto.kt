package com.tonhodevi.codequest.dto

import com.tonhodevi.codequest.models.Exercicio
import com.tonhodevi.codequest.models.Modulo
import com.tonhodevi.codequest.models.Trilha

data class ModuloComPendencias(
    val modulo: Modulo,
    val exerciciosPendentes: List<Exercicio>
)

data class TrilhaComPendencias(
    val trilha: Trilha,
    val modulos: List<ModuloComPendencias>
)