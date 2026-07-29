package com.tonhodevi.codequest.controllers

import com.tonhodevi.codequest.dto.RegistroForm
import com.tonhodevi.codequest.services.EmailJaCadastradoException
import com.tonhodevi.codequest.services.SenhasNaoConferemException
import com.tonhodevi.codequest.services.UsuarioService
import jakarta.validation.Valid
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping

@Controller
class RegistroController(
    private val usuarioService: UsuarioService
) {

    @GetMapping("/registro")
    fun paginaDeRegistro(model: Model): String {
        if (!model.containsAttribute("registroForm")) {
            model.addAttribute("registroForm", RegistroForm())
        }
        return "registro"
    }

    @PostMapping("/registro")
    fun registrar(
        @Valid @ModelAttribute("registroForm") form: RegistroForm,
        bindingResult: BindingResult,
        model: Model
    ): String {
        if (bindingResult.hasErrors()) {
            return "registro"
        }

        try {
            usuarioService.registrar(form)
        } catch (e: SenhasNaoConferemException) {
            model.addAttribute("erro", e.message)
            return "registro"
        } catch (e: EmailJaCadastradoException) {
            model.addAttribute("erro", e.message)
            return "registro"
        }

        return "redirect:/login?registrado"
    }
}