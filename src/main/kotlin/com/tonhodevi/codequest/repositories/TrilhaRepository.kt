package com.tonhodevi.codequest.repositories

import com.tonhodevi.codequest.models.Trilha
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface TrilhaRepository : JpaRepository<Trilha, Long> {
    fun findBySlug(slug: String): Optional<Trilha>
}
