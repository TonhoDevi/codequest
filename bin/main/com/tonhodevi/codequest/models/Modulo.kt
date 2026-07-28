package com.tonhodevi.codequest.models

import jakarta.persistence.*

@Entity
@Table(name = "modulos")
class Modulo(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false)
    var titulo: String,

    // Conteúdo explicativo em Markdown, renderizado no front
    @Column(columnDefinition = "TEXT", nullable = false)
    var conteudoMarkdown: String,

    @Column(nullable = false)
    var ordem: Int = 0,

    @Column(nullable = false)
    var xpRecompensa: Int = 10,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trilha_id", nullable = false)
    var trilha: Trilha


)
