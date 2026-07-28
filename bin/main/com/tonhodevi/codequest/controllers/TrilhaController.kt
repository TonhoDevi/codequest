package com.tonhodevi.codequest.controllers

import com.tonhodevi.codequest.repositories.TrilhaRepository
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@Controller
class TrilhaController(
    private val trilhaRepository: TrilhaRepository
) {

    @GetMapping("/trilhas")
    fun listar(model: Model): String {
        val trilhas = trilhaRepository.findAll().sortedBy { it.ordem }
        model.addAttribute("trilhas", trilhas)
        return "trilhas/lista"
    }

    @GetMapping("/trilhas/{slug}")
    fun detalhe(@PathVariable slug: String, model: Model): String {
        val trilha = trilhaRepository.findBySlug(slug)
            .orElseThrow { NoSuchElementException("Trilha não encontrada") }
        model.addAttribute("trilha", trilha)
        return "trilhas/detalhe"
    }
}
