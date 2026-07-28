package com.tonhodevi.codequest.models

import jakarta.persistence.*

@Entity
@Table(name = "progresso_aluno")
class ProgressoAluno(
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "aluno_id", nullable = false, unique = true)
    var aluno: Usuario,

    @Column(nullable = false)
    var xpTotal: Int = 0,

    @Column(nullable = false)
    var nivel: Int = 1,

    @Column(nullable = false)
    var streakDias: Int = 0

   
) {
    /**
     * Regra simples de nível: a cada 100 XP, sobe 1 nível.
     * Fica fácil de ajustar essa curva depois (ex: exponencial).
     */
    fun adicionarXp(quantidade: Int) {
        xpTotal += quantidade
        nivel = (xpTotal / 100) + 1
    }
}
