# Gerenciador de Jogadores

API REST para cadastro de jogadores de futebol e seus times, com uma interface web que consome a API. Desenvolvi este projeto como atividade da disciplina de **Desenvolvimento Web** do curso de Análise e Desenvolvimento de Sistemas (SENAC SP).

## Funcionalidades

- Cadastrar, listar, alterar e excluir jogadores
- Cadastrar times
- Vincular cada jogador a um time
- Marcar o jogador como lesionado e adicionar uma descrição
- Validar os dados de entrada e devolver mensagens de erro claras
- Interface web em HTML, CSS e JavaScript que consome a API com `fetch` e atualiza a página sem recarregar
- Exibir mensagens de sucesso e de erro, pedir confirmação antes de excluir e avisar quando ainda não há times cadastrados

## Destaques técnicos

- **Arquitetura em camadas** (controller → service → repository), com cada camada responsável por uma parte do sistema
- **DTOs de entrada e de resposta**, para que a API não exponha as entidades JPA diretamente
- **Services definidos por interface** (`JogadorService` e `JogadorServiceJPA`), separando o contrato da implementação
- **Validação com Bean Validation** (`@NotBlank`, `@Size`, `@Min`, `@Max`), feita no backend
- **Tratamento global de exceções** com `@RestControllerAdvice`: erros de validação voltam como `400` com a mensagem de cada campo, e recursos inexistentes voltam como `404` no formato `ProblemDetail` (RFC 9457)

## Tecnologias

- Java 25
- Spring Boot 4
- Spring Web
- Spring Data JPA / Hibernate
- Bean Validation
- H2 (banco de dados em memória)
- Springdoc OpenAPI (Swagger UI)
- HTML, CSS e JavaScript
- Maven

## Como executar

Pré-requisito: **Java 25** instalado. O Maven não é necessário, porque o projeto já vem com o Maven Wrapper.

```bash
git clone https://github.com/GvPOliveira/gerenciador-de-jogadores.git
cd gerenciador-de-jogadores

# Linux / macOS
./mvnw spring-boot:run

# Windows
mvnw.cmd spring-boot:run
```

Com a aplicação rodando:

| O que | Endereço |
| --- | --- |
| Interface web | http://localhost:8080 |
| Documentação da API (Swagger) | http://localhost:8080/swagger-ui.html |
| Console do banco H2 | http://localhost:8080/h2-console |

Para acessar o console do H2, use a JDBC URL `jdbc:h2:mem:testdb`, o usuário `sa` e a senha em branco.

> O banco é em memória: os dados são apagados sempre que a aplicação é reiniciada.

## Endpoints

### Jogadores

| Método | Rota | Descrição | Sucesso |
| --- | --- | --- | --- |
| `GET` | `/jogadores` | Lista todos os jogadores | `200 OK` |
| `GET` | `/jogadores/{id}` | Busca um jogador pelo id | `200 OK` |
| `POST` | `/jogadores` | Cadastra um jogador | `201 Created` |
| `PUT` | `/jogadores/{id}` | Altera um jogador | `200 OK` |
| `DELETE` | `/jogadores/{id}` | Exclui um jogador | `204 No Content` |

### Times

| Método | Rota | Descrição | Sucesso |
| --- | --- | --- | --- |
| `GET` | `/times` | Retorna os times cadastrados (usado para preencher a seleção de time nos formulários) | `200 OK` |
| `POST` | `/times` | Cadastra um time | `201 Created` |

### Erros

| Situação | Status |
| --- | --- |
| Dados inválidos (por exemplo, nome vazio ou camisa fora de 1 a 99) | `400 Bad Request`, com a mensagem de cada campo |
| Jogador ou time não encontrado | `404 Not Found` |

## Exemplo de uso

**Cadastrar um jogador:** `POST /jogadores` (o `PUT /jogadores/{id}` usa o mesmo corpo)

```json
{
  "nome": "Carlos Silva",
  "numeroCamisa": 10,
  "idTime": 1,
  "lesionado": false,
  "descricao": "Meia de criação"
}
```

O `id` do jogador não é enviado no corpo: ele é gerado no cadastro e, na alteração, vem pela URL. Se `lesionado` não for enviado, vale `false`.

**Resposta de `GET /jogadores/1`:**

```json
{
  "id": 1,
  "nome": "Carlos Silva",
  "numeroCamisa": 10,
  "time": { "id": 1, "nome": "Santos" },
  "lesionado": false,
  "descricao": "Meia de criação"
}
```

**Resposta de erro de validação (`400`):**

```json
{
  "nome": "O nome do jogador deve ter no mínimo 2 caracteres.",
  "numeroCamisa": "O número da camisa deve ser no máximo 99."
}
```

### Regras de validação

| Campo | Regra |
| --- | --- |
| Nome do jogador | Obrigatório, com no mínimo 2 caracteres (espaços no início e no fim são ignorados) |
| Número da camisa | Obrigatório, entre 1 e 99 |
| Time | Obrigatório e precisa existir |
| Lesionado | Opcional (padrão `false`) |
| Descrição | Opcional |
| Nome do time | Obrigatório, com no mínimo 2 caracteres (espaços no início e no fim são ignorados) |

**Resposta de recurso não encontrado (`404`):**

```json
{
  "detail": "Jogador não encontrado.",
  "instance": "/jogadores/99",
  "status": 404,
  "title": "Not Found"
}
```

## Estrutura do projeto

```
src/main/java/br/senac/tads/dsw/gerenciadorDeJogadores/
├── controller   # Endpoints REST e tratamento global de exceções
├── service      # Regras de negócio e conversão entre entidade e DTO
├── repository   # Acesso ao banco com Spring Data JPA
├── entity       # Entidades JPA: Jogador e Time
├── dto          # Objetos de entrada e de resposta da API
└── exception    # Exceções de "não encontrado"

src/main/resources/static/
├── index.html / index.js     # Listagem, alteração e exclusão de jogadores e cadastro de times
└── incluir.html / incluir.js # Cadastro de jogadores
```

O relacionamento entre as entidades é de **muitos para um**: cada jogador pertence a um time (`@ManyToOne`), e um time pode ter vários jogadores (`@OneToMany`).

## Telas

<!-- Adicione aqui os prints da aplicação, por exemplo:
![Listagem de jogadores](docs/listagem.png)
![Cadastro de jogador](docs/cadastro.png)
-->

## Contexto acadêmico

Projeto desenvolvido para a disciplina de Desenvolvimento Web (turma 3A Noturno, 2026) do curso de Análise e Desenvolvimento de Sistemas do SENAC SP.

**Uso de IA:** usei o Claude como apoio no desenvolvimento do HTML e do CSS, para aprender a manipular elementos HTML com JavaScript e na escrita deste README.

## Autor

**Gabriel Vinicius**
[Portfólio](https://gvpoliveira.github.io) · [LinkedIn](https://www.linkedin.com/in/gvpoliveira) · [GitHub](https://github.com/GvPOliveira)