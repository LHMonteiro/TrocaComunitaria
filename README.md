# TrocaComunitaria 

MENSAGEM DESTINADA À AVALIÇÃO: O projeto foi feito às pressas, pois não tive tempo nas últimas semanas devido a preparação para o nascimento da minha filha. Por isso não me atentei que deveria seguir o TDD e acabei optando pela abordagem mais rápida para que eu podesse entregar algo. Então vai ter muita coisa faltando nesse projeto e infelizmente eu tive que recorrer ao uso de ia para me ajudar no raciocinio, devido ao curto tempo que eu tinha disponivel. Bom, espero que goste. 

## Sobre o Projeto

O TrocaComunitaria é uma plataforma que permite que usuários publiquem ofertas de produtos ou serviços e os troquem utilizando um sistema interno de créditos. Ao se cadastrar, cada usuário recebe 100 créditos iniciais e pode usá-los para solicitar publicações de outros membros.

## Tecnologias

- **Java 17**
- **Spring Boot 3.2**
- **Spring Data JPA**
- **Banco de dados H2** (em memória)
- **Maven 3.8**

## Funcionalidades

- Cadastro e login de usuários
- Publicação de ofertas (adicionei opção de servirços)
- Feed de publicações disponíveis (não está muito bem estrturada, não tive tempo para mexer nisso)
- Sistema de solicitação de troca
- Aceite ou recusa de solicitações 
- Transferência automática de créditos ao concluir uma troca
- Notificações para o usuário (não está muito bem estrturada, não tive tempo para mexer nisso)
- Histórico de transações (não está muito bem estrturada, não tive tempo para mexer nisso)


## Como executar

### Pré-requisitos

- Java 17 
- Maven 3.8 


### Passos

1. Clone o repositório:
```bash
git clone https://github.com/LHMonteiro/TrocaComunitaria.git
cd TrocaComunitaria
```

2. Para executar à aplicação:
```bash
mvn spring-boot:run
```

3. Acesse .a interface no navegador:
```
http://localhost:8080
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
| luiz Henrique Maques Monteiro | [@LHMonteiro](https://github.com/LHMonteiro) |

## Disciplina

- **Curso:** ciencia da computação
- **Disciplina:** Projeto de porgramação
- **Instituição:** Afya