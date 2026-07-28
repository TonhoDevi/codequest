package com.tonhodevi.codequest.models

import jakarta.persistence.*

enum class TipoExercicio {
    MULTIPLA_ESCOLHA,
    COMPLETAR_CODIGO,
    CODIGO_LIVRE
}

@Entity
@Table(name = "exercicios")
class Exercicio(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false)
    var enunciado: String,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    var tipo: TipoExercicio,

    // Para MULTIPLA_ESCOLHA: alternativas separadas por "|"
    @Column(columnDefinition = "TEXT")
    var alternativas: String? = null,

    // Gabarito: resposta correta (índice, código esperado, ou regex de validação)
    @Column(columnDefinition = "TEXT", nullable = false)
    var respostaCorreta: String,

    @Column(nullable = false)
    var xpRecompensa: Int = 20,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "modulo_id", nullable = false)
    var modulo: Modulo

)
