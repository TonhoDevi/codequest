package com.tonhodevi.codequest.models

import jakarta.persistence.*
import java.time.Instant

enum class Papel {
    ALUNO, PROFESSOR
}

@Entity
@Table(name = "usuarios")
class Usuario(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false)
    var nome: String,

    @Column(nullable = false, unique = true)
    var email: String,

    @Column(nullable = false)
    var senhaHash: String,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    var papel: Papel,

    @Column(nullable = false)
    val criadoEm: Instant = Instant.now()

)
