# Sistema de Aplicação de Crédito

![Java](https://img.shields.io/badge/Java-17-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.0.1-green)
![License](https://img.shields.io/badge/License-MIT-blue)

##  Sobre o Projeto

Este é um sistema completo de aplicação de crédito desenvolvido com Java 17 e Spring Boot 3.0.1 O projeto implementa uma arquitetura robusta para gerenciamento de solicitações de crédito, avaliação de score, processamento de aprovações e notificações aos clientes.

Desenvolvi este sistema com foco em escalabilidade, segurança e boas práticas de desenvolvimento, utilizando os recursos mais recentes do Java 17 e seguindo os princípios de Clean Architecture e Domain-Driven Design.

##  Tecnologias Utilizadas

- **Java 17**: Aproveitando os recursos mais recentes da linguagem
- **Spring Boot 3.0.1**: Framework para desenvolvimento de aplicações Java
- **Spring Security**: Para autenticação e autorização com JWT
- **Spring Data JPA**: Para persistência de dados
- **PostgreSQL**: Banco de dados relacional principal
- **Redis**: Para cache e gerenciamento de sessões
- **Apache Kafka**: Para processamento de eventos assíncronos
- **Swagger/OpenAPI**: Para documentação automática da API
- **Docker**: Para containerização e implantação simplificada
- **Maven**: Para gerenciamento de dependências e build

##  Arquitetura

O sistema segue uma arquitetura em camadas baseada nos princípios de Clean Architecture:

```
┌─────────────────┐
│   Controllers   │ ← API REST, validação de entrada
├─────────────────┤
│    Services     │ ← Lógica de negócio, transações
├─────────────────┤
│  Repositories   │ ← Persistência de dados
├─────────────────┤
│     Domain      │ ← Entidades e regras de negócio
└─────────────────┘
```

### Componentes Principais

- **Módulo de Autenticação**: Gerenciamento de usuários e segurança com JWT
- **Módulo de Clientes**: Cadastro e gerenciamento de clientes
- **Módulo de Score de Crédito**: Cálculo e avaliação de scores
- **Módulo de Aplicação de Crédito**: Processamento de solicitações
- **Módulo de Notificações**: Envio de notificações por SMS

##  Funcionalidades

- ✅ Cadastro e gerenciamento de clientes
- ✅ Solicitação e processamento de aplicações de crédito
- ✅ Cálculo e avaliação de score de crédito
- ✅ Definição de limites de crédito baseados em critérios configuráveis
- ✅ Notificações por SMS para atualizações de status
- ✅ Autenticação segura com JWT
- ✅ API RESTful completa para integração com outros sistemas

##  Requisitos

- Java 17 ou superior
- PostgreSQL 12 ou superior
- Redis 6 ou superior
- Apache Kafka 3.0 ou superior

##  Configuração e Instalação

### Usando Docker (Recomendado)

1. Clone o repositório:
   ```bash
     git clone https://github.com/LeandroBryto/FinanTrack-gestao-de-credito.git
      cd FinanTrack-gestao-de-credito
   ```

2. Execute com Docker Compose:
   ```bash
   docker-compose up -d
   ```

3. Acesse a aplicação em:
   ```
   http://localhost:8080/swagger-ui.html
   ```


##  Autenticação e Segurança

O sistema utiliza JWT (JSON Web Tokens) para autenticação. Para usar a API:

1. Obtenha um token via endpoint de login:
   ```bash
   curl -X POST http://localhost:8080/api/auth/login \
     -H "Content-Type: application/json" \
     -d '{
       "username": "seu_email@exemplo.com",
       "password": "sua_senha"
     }'
   ```

2. Use o token nas requisições subsequentes:
   ```bash
   curl -X GET http://localhost:8080/api/clients \
     -H "Authorization: Bearer seu_token_jwt"
   ```

## 📚 Documentação da API

A documentação completa da API está disponível via Swagger UI:

```
http://localhost:8080/swagger-ui.html
```

### Principais Endpoints

- **Autenticação**: `/api/auth/login`, `/api/auth/refresh`, `/api/auth/logout`
- **Clientes**: `/api/clients`
- **Score de Crédito**: `/api/credit-scores`
- **Aplicações de Crédito**: `/api/credit-applications`


## 🚢 Implantação

### Ambiente de Produção

Para ambientes de produção, recomendo:

1. Configurar HTTPS com certificados válidos
2. Utilizar variáveis de ambiente para senhas e chaves secretas
3. Implementar monitoramento e alertas
4. Configurar backups automáticos do banco de dados

### Configurações Avançadas

O sistema suporta diferentes perfis de execução:
- `dev`: Para desenvolvimento local
- `test`: Para execução de testes
- `prod`: Para ambiente de produção

## 📈 Roadmap

Funcionalidades planejadas para futuras versões:

- [ ] Dashboard administrativo com métricas em tempo real
- [ ] Suporte a múltiplos idiomas
- [ ] App mobile para clientes



## 📄 Licença

Este projeto está licenciado sob a Licença MIT - veja o arquivo [LICENSE](LICENSE) para detalhes.

---

⭐️ Se este projeto foi útil para você, considere dar uma estrela no GitHub! ⭐️



