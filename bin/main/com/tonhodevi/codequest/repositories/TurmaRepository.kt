package com.tonhodevi.codequest.repositories

import com.tonhodevi.codequest.models.Turma
import org.springframework.data.jpa.repository.JpaRepository

interface TurmaRepository : JpaRepository<Turma, Long>
