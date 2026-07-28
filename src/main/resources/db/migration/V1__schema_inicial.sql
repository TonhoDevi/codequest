CREATE TABLE usuarios (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(150) NOT NULL,
    email VARCHAR(150) NOT NULL UNIQUE,
    senha_hash VARCHAR(255) NOT NULL,
    papel VARCHAR(20) NOT NULL,
    criado_em TIMESTAMP NOT NULL DEFAULT now()
);

CREATE TABLE turmas (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(150) NOT NULL,
    data_inicio DATE NOT NULL,
    professor_id BIGINT NOT NULL REFERENCES usuarios(id)
);

CREATE TABLE matriculas (
    id BIGSERIAL PRIMARY KEY,
    aluno_id BIGINT NOT NULL REFERENCES usuarios(id),
    turma_id BIGINT NOT NULL REFERENCES turmas(id),
    matriculado_em TIMESTAMP NOT NULL DEFAULT now(),
    UNIQUE (aluno_id, turma_id)
);

CREATE TABLE trilhas (
    id BIGSERIAL PRIMARY KEY,
    titulo VARCHAR(150) NOT NULL,
    descricao TEXT NOT NULL,
    slug VARCHAR(150) NOT NULL UNIQUE,
    ordem INT NOT NULL DEFAULT 0
);

CREATE TABLE modulos (
    id BIGSERIAL PRIMARY KEY,
    titulo VARCHAR(150) NOT NULL,
    conteudo_markdown TEXT NOT NULL,
    ordem INT NOT NULL DEFAULT 0,
    xp_recompensa INT NOT NULL DEFAULT 10,
    trilha_id BIGINT NOT NULL REFERENCES trilhas(id) ON DELETE CASCADE
);

CREATE TABLE exercicios (
    id BIGSERIAL PRIMARY KEY,
    enunciado TEXT NOT NULL,
    tipo VARCHAR(30) NOT NULL,
    alternativas TEXT,
    resposta_correta TEXT NOT NULL,
    xp_recompensa INT NOT NULL DEFAULT 20,
    modulo_id BIGINT NOT NULL REFERENCES modulos(id) ON DELETE CASCADE
);

CREATE TABLE submissoes (
    id BIGSERIAL PRIMARY KEY,
    aluno_id BIGINT NOT NULL REFERENCES usuarios(id),
    exercicio_id BIGINT NOT NULL REFERENCES exercicios(id),
    resposta_enviada TEXT NOT NULL,
    correta BOOLEAN NOT NULL,
    enviado_em TIMESTAMP NOT NULL DEFAULT now()
);

CREATE TABLE progresso_aluno (
    id BIGSERIAL PRIMARY KEY,
    aluno_id BIGINT NOT NULL UNIQUE REFERENCES usuarios(id),
    xp_total INT NOT NULL DEFAULT 0,
    nivel INT NOT NULL DEFAULT 1,
    streak_dias INT NOT NULL DEFAULT 0
);

-- Índices para as consultas mais comuns
CREATE INDEX idx_modulos_trilha ON modulos(trilha_id);
CREATE INDEX idx_exercicios_modulo ON exercicios(modulo_id);
CREATE INDEX idx_submissoes_aluno ON submissoes(aluno_id);
CREATE INDEX idx_matriculas_aluno ON matriculas(aluno_id);
