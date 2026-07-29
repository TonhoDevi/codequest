package com.tonhodevi.codequest.controllers

import com.tonhodevi.codequest.repositories.UsuarioRepository
import com.tonhodevi.codequest.services.SubmissaoService
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.servlet.mvc.support.RedirectAttributes

@Controller
class SubmissaoController(
    private val submissaoService: SubmissaoService,
    private val usuarioRepository: UsuarioRepository
) {

    @PostMapping("/atividades/exercicios/{exercicioId}/responder")
    fun responder(
        @PathVariable exercicioId: Long,
        @RequestParam resposta: String,
        authentication: Authentication,
        redirectAttributes: RedirectAttributes
    ): String {
        val aluno = usuarioRepository.findByEmail(authentication.name)
            .orElseThrow { NoSuchElementException("Usuário não encontrado") }

        val resultado = submissaoService.submeter(aluno.id!!, exercicioId, resposta)

        if (resultado.correta) {
            redirectAttributes.addFlashAttribute(
                "mensagemSucesso", "Resposta correta! +${resultado.xpGanho} XP"
            )
        } else {
            redirectAttributes.addFlashAttribute("mensagemErro", "Resposta incorreta, tente novamente.")
        }

        return "redirect:/atividades"
    }
}