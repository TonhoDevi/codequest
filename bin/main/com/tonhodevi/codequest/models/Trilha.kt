package com.tonhodevi.codequest.models

import jakarta.persistence.*

@Entity
@Table(name = "trilhas")
class Trilha(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false)
    var titulo: String,

    @Column(nullable = false)
    var descricao: String,

    // ex: "jdbc", "postgresql-avancado" — usado na URL amigável
    @Column(nullable = false, unique = true)
    var slug: String,

    @Column(nullable = false)
    var ordem: Int = 0,

    @OneToMany(mappedBy = "trilha", cascade = [CascadeType.ALL], orphanRemoval = true)
    @OrderBy("ordem ASC")
    var modulos: MutableList<Modulo> = mutableListOf()

   
)
