package com.tonhodevi.codequest.repositories

import com.tonhodevi.codequest.models.Exercicio
import org.springframework.data.jpa.repository.JpaRepository

interface ExercicioRepository : JpaRepository<Exercicio, Long> {
    fun findByModuloId(moduloId: Long): List<Exercicio>
}
