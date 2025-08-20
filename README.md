# Fretec - Serviço de Autenticação

Este serviço é responsável pelo **cadastro**, **login** e **autenticação** de usuários da plataforma **Fretec**, utilizando **Java 17**, **Spring Boot**, **Spring Security com JWT**, **MongoDB** e arquitetura **Clean Architecture**. Logs são enviados via **Logstash** para o **Graylog**, garantindo rastreabilidade e observabilidade.

---

## Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 3.x**
- **Spring Security + JWT**
- **MongoDB**
- **Logstash + Graylog**
- **Clean Architecture**
- **Maven**

---

## Funcionalidades

- Cadastro de novos usuários
- Login com geração de token JWT
- Validação de credenciais
- Proteção de endpoints com autenticação JWT
- Logs estruturados enviados ao Graylog
- Separação de camadas conforme Clean Architecture:
    - `domain`
    - `application`

---