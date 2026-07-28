package com.tonhodevi.codequest.models

import jakarta.persistence.*
import java.time.Instant

@Entity
@Table(name = "submissoes")
class Submissao(


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "aluno_id", nullable = false)
    var aluno: Usuario,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exercicio_id", nullable = false)
    var exercicio: Exercicio,

    @Column(columnDefinition = "TEXT", nullable = false)
    var respostaEnviada: String,

    @Column(nullable = false)
    var correta: Boolean,

    @Column(nullable = false)
    val enviadoEm: Instant = Instant.now()

)
