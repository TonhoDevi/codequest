package com.tonhodevi.codequest.repositories

import com.tonhodevi.codequest.models.ProgressoAluno
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface ProgressoAlunoRepository : JpaRepository<ProgressoAluno, Long> {
    fun findByAlunoId(alunoId: Long): Optional<ProgressoAluno>
}
