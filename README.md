# Resumo API StarWars

API que contém os dados dos planetas, como: nome, clima e terreno. Ao cadastrar, é feito uma consulta (REST) a API pública do Star Wars (https://swapi.co/) para saber a quantidade de aparições em filmes desse planeta.
Foi utilizado: 
- Java 8
- Spring Boot 
- Docker
- MongoDB (foi criado uma base no serviço de banco de dados mLab)
- Testes com Rest Assured e JUnit 5
- Documentação com Swagger

## Pré requisitos

- Possuir o git
- Possuir o maven
- Possuir o docker
    - Caso faça o build direto pela IDE, não é necessário instalar o mongo pois a aplicação acessa um serviço externo)

## Funcionamento

- Primeiro clone o projeto
- Em seguida, acesse a pasta do projeto e executar os comandos abaixo:
    - mvn clean install
    - docker-compose up
- Feito isso, o projeto estará rodando no http://localhost:8080

## Documentação

- Após executar a instalação, o projeto irá rodar num container na porta 8080, é importante que esta porta não esteja em uso.
- O projeto foi documentado usando o Swagger, com isso basta acessar http://localhost:8080/swagger-ui.html#/ para ter acesso aos endpoints.
