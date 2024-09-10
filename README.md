<h1 align="center"> Lista de Taredas </h1>

## Descrição do Projeto
Este projeto é uma API de uma aplicação de lista de tarefas deita com Java e Spring Boot. A API permite que os usuários criem listas, e dentro de cada lista sejam adicionadas atividas a serem realizadas.

## Tecnologias Utilizadas
- ``Java 21``
- ``Spring 3.3.3``
- ``Maven``
- ``Mysql``
- ``SpringDoc``
- ``Lombok``
- ``DevTools``
- ``Junit``
- ``Mockito``

## Funcionalidades

- ``Criação de Listas``
- ``Criação de Tarefas``
- ``Listagem de Listas``
- ``Listagem de Tarefas com base na prioridade``
- ``Listagem de Tarefas por nome``

## Endpoints
### Abaixo você pode conferir os endpoints da aplicação:
![image](https://github.com/user-attachments/assets/a1fa6835-36a4-4421-8a8c-3f92c87c4df1)

## Entenda os Endpoints
- ``/api/lists: Endpoint de tipo Post onde é feita a criação de uma lista``
- ``/api/list/all: Endpoint de tipo GET onde é feita o retorno de todas as listas paginadas``
- ``/api/list/{id}: Endpoint de tipo GET onde é feita o retorno de uma lista especifica com base no seu id``
- ``/api/list/update{id}: Endpoint de tipo PUT onde é feita a atualização de uma lista com base no seu ID``
- ``/api/list/delete/{id}: Endpoint de tipo DELETE onde é feita a exclusão de uma lista (A lista não é excluida permanentemente do banco, apenas desativada para o usuario)``
- ``/api/items: Endpoint de tipo POST onde é feita a criação de um item``
- ``/api/items/{id}: Endpoint de tipo GET onde é feito o retorno de um item com brase no seu ID ``
- ``/api/items/{listId}/search: Endpoint de tipo GET onde é feito o retorno de um item com base no ID da sua lista e no seu nome``
- ``/api/items/update/{id}: Endpoint de tipo PUT onde é feito a atualização de um item com base no seu ID``
- ``/api/items/{listId}/search: Endpoint de tipo DELETE onde é feita a exclusão de um item (O item não é excluido permanentemente do banco, apenas desativada para o usuario)``  

## Entenda as entidades

### List Items
Entidade que representa a lista de items, ela é composta por:
- ``Id: Indicador unico da entidade``
- ``Name: Nome da lista``
- ``Start Date: Data de inicialização da lista.``
- ``End Date: Data que a lista é desativada/excluida``
- ``Is Active: Indica se a lista ainda está ativa``
- ``items: Lista dos items``

### Items
Entidade que representa os items que forma uma lista:
- ``Id: Indicador unico da entidade``
- ``Name: Nome do item``
- ``Description: Descrição do que deve ser aquele item``
- ``Start Date: Data de inicialização do item.``
- ``Status: O Status que aquele item se encontra (Status é um ENUM que pode ser PENDENTE, EM_ANDAMENTO, FEITO``
- ``End Date: Data que o item é desativada/excluida``
- ``Is Active: Indica se o item ainda está ativo``
- ``Priority: Boolean que diz se o item é prioridade ou não (Itens marcados como prioridade aparecem primeiro na listagem)``
- ``List Item: Lista aquele item pertence``

## Configuração do Ambiente de Desenvolvimento e execução

1. Clone o repositório: git clone https://github.com/PedroUchoa/Spring-Lista-Tarefas.git
2. Abra o projeto em sua IDE preferida.
3. Configure o arquivo application.properties com as informações do banco de dados.
4. Execute o projeto localmente através da IDE ou usando o maven.
5. Acesse a aplicação em http://localhost:8080.
6. Tambem é possivel acessar uma documentação do Springdoc em http://localhost:8080/swagger-ui.html.

## Sobre os testes
Sobre os testes, eles foram realizados utlizando JUNIT5 e Mockito. Apenas foram feitos testes para as Services pois os Repositorys apenas utilizam Querys derivadas que já são automaticamente testadas pela JPA.

## Contato
### João Pedro Uchôa Campos Olimpio- Desenvolvedor Java
### Email: joaopedrouchoacamposolimpio@gmail.com
