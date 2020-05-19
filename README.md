## Índice

 <ol>
  <li><a href="#Sobre">Sobre o projeto</a></li>
  <li><a href="#Tecnologias">Tecnologias utilizadas</a></li>
  <li><a href="#Pre">Pré-requisitos</a></li>
  <li><a href="#Testes">Testes</a></li>
  <li><a href="#Instalacao">Instalação</a>
  <li><a href="#Rotas">Rotas</a>
  <li><a href="#final">Considerações finais</a>
 
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
- Possuir o docker (caso vá rodar por ele, pois também é possível executar diretamente da IDE visto que o mongodb está em um serviço online)

### <a name="Testes">4. Efetuando testes</a>  

&nbsp;&nbsp;&nbsp;&nbsp;Testes

### <a name="Instalacao">5. Instalação</a>

- Clonar o projeto
- Após a clonagem, é necessário acessar a pasta do projeto e executar os comandos abaixo:
    - mvn clean install
    - docker-compose up
- Pronto! O projeto estará rodando no http://localhost:8080

### <a name="Rotas">6. Rotas</a>

| Requisição | Caminho                  | Função |
| --- | --- | --- |
| GET        | /api/planetas            | Lista todos os planetas
| POST       | /api/planetas            | Cria um planeta
| DELETE     | /api/planetas/id/{id}    | Remove um planeta
| GET        | /api/planetas/id/{id}    | Busca um planeta por ID
| GET        | /api/planetas/nome/{nome}| Busca um planeta pelo Nome
