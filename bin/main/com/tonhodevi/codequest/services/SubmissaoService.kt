package com.tonhodevi.codequest.services

import com.tonhodevi.codequest.models.ProgressoAluno
import com.tonhodevi.codequest.models.Submissao
import com.tonhodevi.codequest.repositories.ExercicioRepository
import com.tonhodevi.codequest.repositories.ProgressoAlunoRepository
import com.tonhodevi.codequest.repositories.SubmissaoRepository
import com.tonhodevi.codequest.repositories.UsuarioRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

data class ResultadoSubmissao(
    val correta: Boolean,
    val xpGanho: Int,
    val xpTotalAtual: Int,
    val nivelAtual: Int
)

@Service
class SubmissaoService(
    private val exercicioRepository: ExercicioRepository,
    private val usuarioRepository: UsuarioRepository,
    private val submissaoRepository: SubmissaoRepository,
    private val progressoRepository: ProgressoAlunoRepository
) {

    @Transactional
    fun submeter(alunoId: Long, exercicioId: Long, resposta: String): ResultadoSubmissao {
        val exercicio = exercicioRepository.findById(exercicioId)
            .orElseThrow { NoSuchElementException("Exercício não encontrado") }
        val aluno = usuarioRepository.findById(alunoId)
            .orElseThrow { NoSuchElementException("Aluno não encontrado") }

        val correta = avaliarResposta(resposta, exercicio.respostaCorreta)

        submissaoRepository.save(
            Submissao(
                aluno = aluno,
                exercicio = exercicio,
                respostaEnviada = resposta,
                correta = correta
            )
        )

        val progresso = progressoRepository.findByAlunoId(alunoId)
            .orElseGet { ProgressoAluno(aluno = aluno) }

        val xpGanho = if (correta) exercicio.xpRecompensa else 0
        if (correta) {
            progresso.adicionarXp(xpGanho)
            progressoRepository.save(progresso)
        }

        return ResultadoSubmissao(
            correta = correta,
            xpGanho = xpGanho,
            xpTotalAtual = progresso.xpTotal,
            nivelAtual = progresso.nivel
        )
    }

    /**
     * Comparação simples por enquanto (normaliza espaços/caixa).
     * Para CODIGO_LIVRE, dá pra evoluir para execução em sandbox ou testes automatizados.
     */
    private fun avaliarResposta(resposta: String, gabarito: String): Boolean {
        return resposta.trim().equals(gabarito.trim(), ignoreCase = true)
    }
}
