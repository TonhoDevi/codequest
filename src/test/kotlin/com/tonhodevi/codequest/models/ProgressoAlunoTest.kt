package com.tonhodevi.codequest.models

import com.tonhodevi.codequest.models.Papel
import com.tonhodevi.codequest.models.Usuario
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class ProgressoAlunoTest : StringSpec({

    val alunoFake = Usuario(
        nome = "Aluno Teste",
        email = "aluno@teste.com",
        senhaHash = "hash",
        papel = Papel.ALUNO
    )

    "deve começar no nível 1 com 0 XP" {
        val progresso = ProgressoAluno(aluno = alunoFake)
        progresso.nivel shouldBe 1
        progresso.xpTotal shouldBe 0
    }

    "deve acumular XP corretamente" {
        val progresso = ProgressoAluno(aluno = alunoFake)
        progresso.adicionarXp(30)
        progresso.xpTotal shouldBe 30
    }

    "deve subir de nível a cada 100 XP" {
        val progresso = ProgressoAluno(aluno = alunoFake)
        progresso.adicionarXp(250)
        progresso.xpTotal shouldBe 250
        progresso.nivel shouldBe 3
    }
})
