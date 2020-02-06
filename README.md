# banco-java

## Informações do projeto
Aplicação escrita utilizando a linguagem Java 8 em conjunto com Spring-Boot para levantar um serviço Web e o Spring-Data-JPA para acessar a camada do banco de dados.

Foi utilizada uma base de dados H2 em memória para realizar a gerência dos dados durante o fluxo da aplição, sendo assim, ao reiniciar o servidor, os dados são perdidos.

## Informações para compilar e executar o projeto.
Este projeto foi construído utilizando o Maven para controle de dependências, assim, será necessário utilizá-lo para executar o projeto.

Ao clonar o projeto, abra o console e utilize o comando para baixar as dependências.

Caso esteja usando Eclipse como IDE, o comando "mvn eclipse:eclipse -DdownloadSources=true -DdownloadJavadocs=true", irá baixar as dependências necessárias, além dos fontes e do javadoc destas, e ainda irá configurar o projeto para o formato do Eclipse.

Após este passo, é importante executar o comando "mvn clean install", com este comando o Maven irá realizar uma série de processos nas fases do compilação dele, como: buildar as classes java, executar os testes e gerar um executável. Este passo é importante pois ele ajustará o arquivo de propriedades do projeto de forma adequada para execução, mesmo que a aplicação venha a ser executada pela IDE posteriormente.

Para executar a aplicação pelo console, existe um comando simples: "mvn spring-boot:run".

## Documentação da API
Foi adicionada a dependência do SWAGGER ao projeto, para que exista uma documentação de fácil acesso ao que foi implementado na API.

É possível acessar esta documentação pela endereço padrão, após inicar a aplicação: http://localhost:8080/swagger-ui.html

## Informações adicionais
Para montar a estrutura da base de dados, foi adicionada dependência do Flyway, por isso, sempre que a apicação iniciar, os scripts da pasta "db.migration" serão executados e a base H2 estará em um estado conhecido para o seu uso.
