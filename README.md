# API STAR WARS

## Índice

 <ol>
  <li><a href="#Sobre">Sobre o projeto</a></li>
  <li><a href="#Tecnologias">Tecnologias utilizadas</a></li>
  <li><a href="#Pre">Pré-requisitos</a></li>
  <li><a href="#Instalacao">Instalação</a>
   <li><a href="#Testes">Testes</a></li>
  <li><a href="#Rotas">Rotas</a>
  <li><a href="#Doc">Documentação</a>
 
</ol> 

<dl>
  
### <a name="Sobre">1. Sobre o projeto</a> 

&nbsp;&nbsp;&nbsp;&nbsp;API que contém os dados dos planetas, como: nome, clima e terreno. Ao cadastrar, é feito uma consulta (REST) a API pública do Star Wars (https://swapi.dev/) para saber a quantidade de aparições em filmes desse planeta.

### <a name="Tecnologias">2. Tecnologias utilizadas</a>

- Java 8 com a IDE Intellij
- Spring Boot 
- Docker
- MongoDB (foi criado uma base no serviço de banco de dados mLab)
- Testes com Rest Assured, JUnit 5 e Postman
- Documentação com Swagger

### <a name="Pre">3. Pré-requisitos</a>

- Possuir o git
- Possuir o maven
- Possuir o docker
   - Caso faça o build o projeto direto pela IDE, não será necessário instalar o mongo localmente pois o projeto utiliza um serviço externo.

### <a name="Instalacao">4. Instalação</a>

- Clonar o projeto
- Em seguida é necessário acessar a pasta do projeto e executar os comandos abaixo:
    - *mvn clean package*
    - *docker-compose up*
- Feito isso, o projeto estará rodando em **http://localhost:8080**

### <a name="Testes">5. Efetuando testes</a>  

&nbsp;&nbsp;&nbsp;&nbsp;Ao executar o comando *mvn clean package* na instalação os testes serão executados.
Para testes nos endpoints, passando pelo Controller, foi utilizado o framework open-souce Rest Assured. Para testar os serviços que acessam o mongodb, foi utilizado JUnit 5. Há também testes no Postman.

### <a name="Rotas">6. Rotas</a>

| Requisição | Caminho                  | Função |
| --- | --- | --- |
| GET        | /api/planetas            | Lista todos os planetas
| POST       | /api/planetas            | Cria um planeta
| DELETE     | /api/planetas/id/{id}    | Remove um planeta
| GET        | /api/planetas/id/{id}    | Busca um planeta por ID
| GET        | /api/planetas/nome/{nome}| Busca um planeta pelo Nome

### <a name="Doc">7. Documentação</a>

- O projeto foi documentado usando o Swagger, com isso basta acessar **http://localhost:8080/swagger-ui.html#/** para ter acesso aos endpoints.


