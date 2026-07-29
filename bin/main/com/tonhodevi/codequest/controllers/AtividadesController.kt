package com.tonhodevi.codequest.controllers

import com.tonhodevi.codequest.dto.ModuloComPendencias
import com.tonhodevi.codequest.dto.TrilhaComPendencias
import com.tonhodevi.codequest.repositories.ExercicioRepository
import com.tonhodevi.codequest.repositories.ProgressoAlunoRepository
import com.tonhodevi.codequest.repositories.SubmissaoRepository
import com.tonhodevi.codequest.repositories.TrilhaRepository
import com.tonhodevi.codequest.repositories.UsuarioRepository
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
class AtividadesController(
    private val trilhaRepository: TrilhaRepository,
    private val exercicioRepository: ExercicioRepository,
    private val submissaoRepository: SubmissaoRepository,
    private val usuarioRepository: UsuarioRepository,
    private val progressoRepository: ProgressoAlunoRepository
) {

    @GetMapping("/atividades")
    fun listar(model: Model, authentication: Authentication): String {
        val aluno = usuarioRepository.findByEmail(authentication.name)
            .orElseThrow { NoSuchElementException("Usuário não encontrado") }

        val idsRespondidosCorretamente = submissaoRepository
            .findByAlunoIdAndCorretaTrue(aluno.id!!)
            .map { it.exercicio.id }
            .toSet()

        val trilhasComPendencias = trilhaRepository.findAll()
            .sortedBy { it.ordem }
            .mapNotNull { trilha ->
                val modulosComPendencias = trilha.modulos
                    .sortedBy { it.ordem }
                    .mapNotNull { modulo ->
                        val pendentes = exercicioRepository.findByModuloId(modulo.id!!)
                            .filterNot { idsRespondidosCorretamente.contains(it.id) }
                        if (pendentes.isEmpty()) null else ModuloComPendencias(modulo, pendentes)
                    }
                if (modulosComPendencias.isEmpty()) null else TrilhaComPendencias(trilha, modulosComPendencias)
            }

        val totalPendentes = trilhasComPendencias.sumOf { t -> t.modulos.sumOf { it.exerciciosPendentes.size } }

        model.addAttribute("trilhasComPendencias", trilhasComPendencias)
        model.addAttribute("totalPendentes", totalPendentes)
        model.addAttribute("progresso", progressoRepository.findByAlunoId(aluno.id!!).orElse(null))
        return "atividades/lista"
    }
}