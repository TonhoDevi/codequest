package com.tonhodevi.codequest.repositories

import com.tonhodevi.codequest.models.Submissao
import org.springframework.data.jpa.repository.JpaRepository

interface SubmissaoRepository : JpaRepository<Submissao, Long> {
    fun findByAlunoIdAndExercicioId(alunoId: Long, exercicioId: Long): List<Submissao>
    fun findByAlunoIdAndCorretaTrue(alunoId: Long): List<Submissao>
}
