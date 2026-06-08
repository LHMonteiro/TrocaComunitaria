# TrocaComunitaria 

Sistema de troca de produtos e serviços baseado em tokens de crédito entre membros de uma comunidade.

## Sobre o Projeto

O TrocaComunitaria é uma plataforma que permite que usuários publiquem ofertas de produtos ou serviços e os troquem utilizando um sistema interno de créditos. Ao se cadastrar, cada usuário recebe 100 créditos iniciais e pode usá-los para solicitar publicações de outros membros.

## Tecnologias

- **Java 17**
- **Spring Boot 3.2**
- **Spring Data JPA**
- **Banco de dados H2** (em memória)
- **Maven**

## Funcionalidades

- Cadastro e login de usuários
- Publicação de ofertas (produtos ou serviços)
- Feed de publicações disponíveis
- Sistema de solicitação de troca
- Aceite ou recusa de solicitações
- Transferência automática de créditos ao concluir uma troca
- Notificações para o usuário
- Histórico de transações

## Como executar

### Pré-requisitos

- Java 17 ou superior
- Maven 3.8 ou superior

### Passos

1. Clone o repositório:
```bash
git clone https://github.com/LHMonteiro/TrocaComunitaria.git
cd TrocaComunitaria
```

2. Execute a aplicação:
```bash
mvn spring-boot:run
```

3. Acesse a interface no navegador:
```
http://localhost:8080
```

4. Para acessar o console do banco de dados H2:
```
http://localhost:8080/h2-console
JDBC URL: jdbc:h2:mem:marketplace
Usuário: sa
Senha: (deixar em branco)
```

## Endpoints da API

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| POST | `/api/usuarios` | Cadastrar usuário |
| POST | `/api/usuarios/login` | Login |
| GET | `/api/usuarios/{id}` | Buscar usuário por ID |
| POST | `/api/publicacoes` | Criar publicação |
| GET | `/api/publicacoes/feed/{usuarioId}` | Ver feed de publicações |
| GET | `/api/publicacoes/usuario/{usuarioId}` | Ver publicações do usuário |
| PATCH | `/api/publicacoes/{id}/cancelar` | Cancelar publicação |
| POST | `/api/solicitacoes/publicacao/{publicacaoId}` | Solicitar publicação |
| GET | `/api/solicitacoes/recebidas/{usuarioId}` | Ver solicitações recebidas |
| POST | `/api/solicitacoes/{id}/aceitar` | Aceitar solicitação |
| POST | `/api/solicitacoes/{id}/recusar` | Recusar solicitação |
| GET | `/api/notificacoes/usuario/{usuarioId}` | Ver notificações |
| GET | `/api/transacoes/usuario/{usuarioId}` | Ver histórico de transações |

## Estrutura do Projeto

```
src/
└── main/
    ├── java/br/com/economiacircular/plataforma/
    │   ├── domain/          # Entidades do domínio
    │   ├── repository/      # Repositórios JPA
    │   ├── api/             # Controllers REST
    │   │   └── dto/         # Objetos de requisição
    │   └── PlataformaApplication.java
    └── resources/
        ├── application.properties
        └── static/
            └── index.html   # Interface web
```

## Integrantes

| Nome | GitHub |
|------|--------|
| Henrique Monteiro | [@LHMonteiro](https://github.com/LHMonteiro) |

## Disciplina

- **Curso:** Nome do Curso
- **Disciplina:** Orientação a Objetos
- **Instituição:** Nome da Instituição