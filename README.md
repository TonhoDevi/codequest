# CodeQuest

CodeQuest é uma plataforma web em Kotlin + Spring Boot para ensino de programação com trilhas, exercícios práticos, progresso do aluno e gamificação.

O projeto foi pensado como uma aplicação de estudo e portfólio, com foco em organização por domínio e evolução para um sistema mais completo de ensino online.

## ✨ Funcionalidades

- Gestão de usuários com papéis de aluno e professor
- Cadastro e autenticação com Spring Security
- Trilhas de aprendizagem com módulos e ordem pedagógica
- Exercícios com tipos como múltipla escolha, completar código e código livre
- Registro de submissões e correção simples de respostas
- Sistema de XP, nível e streak para acompanhar o progresso
- Interface web com Thymeleaf e templates para páginas de trilhas

## 🛠️ Stack

| Camada | Tecnologia |
|---|---|
| Linguagem | Kotlin |
| Framework | Spring Boot 3.3.x |
| Banco de dados | PostgreSQL |
| Migrações | Flyway |
| Template engine | Thymeleaf |
| Segurança | Spring Security |
| Documentação API | springdoc-openapi |
| Testes | Kotest + MockK |
| Build | Gradle (Kotlin DSL) |

## 📂 Estrutura do projeto

```text
src/main/kotlin/com/tonhodevi/codequest
├── config/         # Configurações gerais da aplicação
├── controllers/    # Controllers web
├── models/         # Entidades do domínio
├── repositories/   # Repositórios JPA
├── services/       # Regras de negócio e serviços
└── resources/
    ├── templates/  # Páginas HTML com Thymeleaf
    ├── static/     # CSS, JS e arquivos estáticos
    └── db/migration/  # Scripts Flyway
```

## 🚀 Como rodar localmente

### Pré-requisitos

- JDK 17+
- PostgreSQL em execução
- Gradle disponível no terminal

### Passos

1. Crie um banco de dados chamado `codequest`.
2. Ajuste as credenciais, se necessário, via variáveis de ambiente:

```bash
export DB_USERNAME=postgres
export DB_PASSWORD=postgres
```

3. Inicie a aplicação:

```bash
gradle bootRun
```

A aplicação ficará disponível em:

- http://localhost:8080
- Documentação Swagger em http://localhost:8080/docs

## 🧪 Testes

```bash
gradle test
```

## 🗺️ Próximos passos

- CRUD completo de turmas e alunos no painel do professor
- Administração de trilhas, módulos e exercícios
- Melhorias na correção de exercícios de código livre
- Sistema de badges e conquistas
- Ranking de XP por turma

## 📄 Licença

Projeto pessoal para fins de estudo, portfólio e uso educacional.
