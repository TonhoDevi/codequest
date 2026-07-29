package com.tonhodevi.codequest.services

import com.tonhodevi.codequest.dto.RegistroForm
import com.tonhodevi.codequest.models.Papel
import com.tonhodevi.codequest.models.Usuario
import com.tonhodevi.codequest.repositories.UsuarioRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

class EmailJaCadastradoException(mensagem: String) : RuntimeException(mensagem)
class SenhasNaoConferemException(mensagem: String) : RuntimeException(mensagem)

@Service
class UsuarioService(
    private val usuarioRepository: UsuarioRepository,
    private val passwordEncoder: PasswordEncoder
) {

    fun registrar(form: RegistroForm): Usuario {
        if (form.senha != form.confirmarSenha) {
            throw SenhasNaoConferemException("As senhas não conferem")
        }

        if (usuarioRepository.existsByEmail(form.email)) {
            throw EmailJaCadastradoException("Já existe uma conta com este e-mail")
        }

        val usuario = Usuario(
            nome = form.nome,
            email = form.email,
            senhaHash = passwordEncoder.encode(form.senha),
            papel = Papel.ALUNO
        )

        return usuarioRepository.save(usuario)
    }
}
