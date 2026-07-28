package com.tonhodevi.codequest.repositories

import com.tonhodevi.codequest.models.Matricula
import org.springframework.data.jpa.repository.JpaRepository

interface MatriculaRepository : JpaRepository<Matricula, Long> {
    fun findByAlunoId(alunoId: Long): List<Matricula>
}
