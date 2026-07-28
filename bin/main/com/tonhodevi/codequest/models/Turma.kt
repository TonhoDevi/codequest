package com.tonhodevi.codequest.models

import jakarta.persistence.*
import java.time.LocalDate

@Entity
@Table(name = "turmas")
class Turma(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false)
    var nome: String,

    @Column(nullable = false)
    var dataInicio: LocalDate,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "professor_id", nullable = false)
    var professor: Usuario

)
