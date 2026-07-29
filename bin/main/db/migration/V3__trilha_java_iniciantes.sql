-- ===== Trilha: Java para Iniciantes =====
INSERT INTO trilhas (titulo, descricao, slug, ordem) VALUES
('Java para Iniciantes', 'Primeiros passos em Java: imprimir na tela, variáveis e leitura de dados com Scanner.', 'java-iniciantes', 0);

-- ===== Módulo 1: Primeiros passos =====
INSERT INTO modulos (titulo, conteudo_markdown, ordem, xp_recompensa, trilha_id)
SELECT 'Primeiros passos', '# Primeiros passos em Java

Todo programa Java precisa de uma classe e de um método `main`:

````java
public class Main {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
    }
}
````

`System.out.println(...)` imprime um texto no console, seguido de uma quebra de linha.', 1, 10, id
FROM trilhas WHERE slug = 'java-iniciantes';

INSERT INTO exercicios (enunciado, tipo, alternativas, resposta_correta, xp_recompensa, modulo_id)
SELECT 'Qual comando imprime um texto no console em Java?', 'MULTIPLA_ESCOLHA',
       'System.out.println|print|console.log|echo', 'System.out.println', 10, m.id
FROM modulos m JOIN trilhas t ON m.trilha_id = t.id
WHERE t.slug = 'java-iniciantes' AND m.titulo = 'Primeiros passos';

INSERT INTO exercicios (enunciado, tipo, alternativas, resposta_correta, xp_recompensa, modulo_id)
SELECT 'Complete o código para imprimir "Hello, World!": System.out.___("Hello, World!");', 'COMPLETAR_CODIGO',
       NULL, 'println', 10, m.id
FROM modulos m JOIN trilhas t ON m.trilha_id = t.id
WHERE t.slug = 'java-iniciantes' AND m.titulo = 'Primeiros passos';

INSERT INTO exercicios (enunciado, tipo, alternativas, resposta_correta, xp_recompensa, modulo_id)
SELECT 'Escreva um programa Java completo (com classe Main e método main) que imprime Hello, World! no console.', 'CODIGO_LIVRE',
       NULL, 'public class Main {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
    }
}', 20, m.id
FROM modulos m JOIN trilhas t ON m.trilha_id = t.id
WHERE t.slug = 'java-iniciantes' AND m.titulo = 'Primeiros passos';

-- ===== Módulo 2: Variáveis e Scanner =====
INSERT INTO modulos (titulo, conteudo_markdown, ordem, xp_recompensa, trilha_id)
SELECT 'Variáveis e Scanner', '# Lendo dados do usuário

Para ler o que o usuário digita no console, usamos a classe `Scanner`:

````java
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner leitor = new Scanner(System.in);
        System.out.println("Qual é o seu nome?");
        String nome = leitor.nextLine();
        System.out.println("Olá, " + nome + "!");
    }
}
```', 2, 15, id
FROM trilhas WHERE slug = 'java-iniciantes';

INSERT INTO exercicios (enunciado, tipo, alternativas, resposta_correta, xp_recompensa, modulo_id)
SELECT 'Qual classe do Java é usada para ler dados digitados pelo usuário no console?', 'MULTIPLA_ESCOLHA',
       'Scanner|Reader|Input|BufferedWriter', 'Scanner', 10, m.id
FROM modulos m JOIN trilhas t ON m.trilha_id = t.id
WHERE t.slug = 'java-iniciantes' AND m.titulo = 'Variáveis e Scanner';

INSERT INTO exercicios (enunciado, tipo, alternativas, resposta_correta, xp_recompensa, modulo_id)
SELECT 'Complete o código para criar um Scanner: Scanner leitor = new ___(System.in);', 'COMPLETAR_CODIGO',
       NULL, 'Scanner', 10, m.id
FROM modulos m JOIN trilhas t ON m.trilha_id = t.id
WHERE t.slug = 'java-iniciantes' AND m.titulo = 'Variáveis e Scanner';

INSERT INTO exercicios (enunciado, tipo, alternativas, resposta_correta, xp_recompensa, modulo_id)
SELECT 'Escreva um programa Java que usa Scanner para ler o nome digitado pelo usuário e imprime "Olá, " seguido do nome.', 'CODIGO_LIVRE',
       NULL, 'import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner leitor = new Scanner(System.in);
        String nome = leitor.nextLine();
        System.out.println("Olá, " + nome);
    }
}', 25, m.id
FROM modulos m JOIN trilhas t ON m.trilha_id = t.id
WHERE t.slug = 'java-iniciantes' AND m.titulo = 'Variáveis e Scanner';
