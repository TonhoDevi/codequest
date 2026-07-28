package com.tonhodevi.codequest.models

import jakarta.persistence.*
import java.time.Instant

@Entity
@Table(name = "matriculas", uniqueConstraints = [
    UniqueConstraint(columnNames = ["aluno_id", "turma_id"])
])
class Matricula(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "aluno_id", nullable = false)
    var aluno: Usuario,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "turma_id", nullable = false)
    var turma: Turma,

    @Column(nullable = false)
    val matriculadoEm: Instant = Instant.now()


)
