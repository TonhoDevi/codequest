package com.tonhodevi.codequest.services

import com.tonhodevi.codequest.repositories.UsuarioRepository
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class UsuarioDetailsService(
    private val usuarioRepository: UsuarioRepository
) : UserDetailsService {

    override fun loadUserByUsername(email: String): UserDetails {
        val usuario = usuarioRepository.findByEmail(email)
            .orElseThrow { UsernameNotFoundException("Usuário não encontrado: $email") }

        return User.builder()
            .username(usuario.email)
            .password(usuario.senhaHash)
            .authorities(SimpleGrantedAuthority("ROLE_${usuario.papel.name}"))
            .build()
    }
}
