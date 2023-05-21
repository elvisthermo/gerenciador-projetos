# Gerenciador-projetos

![Java](https://badgen.net/badge/Language/Java/red)
![Spring Boot](https://badgen.net/badge/Framework/Spring%20Boot/green)
![PostgreSQL](https://badgen.net/badge/Database/Postgre/blue)
![Docker](https://badgen.net/badge/Containerization/Docker/cyan)


### Descrição:
O projeto é um aplicativo web desenvolvido com o framework Java Spring Boot que permite gerenciar projetos por meio da troca de dados no formato JSON. (JavaScript Object Notation) é um formato leve de intercâmbio de dados que é amplamente utilizado em aplicações web.

### Funcionalidades:

Criação de Projetos: Os usuários podem criar novos projetos fornecendo informações como título, descrição, datas de início e conclusão, e outras informações relevantes. Esses dados são enviados em formato JSON para o servidor.

Listagem de Projetos: O aplicativo exibe uma lista dos projetos existentes, mostrando os títulos e outras informações resumidas de cada um.

Detalhes do Projeto: Ao selecionar um projeto na lista, os detalhes completos do projeto são exibidos, incluindo a descrição, datas, status do projeto e outras informações relevantes.

Atualização de Projetos: Os usuários podem editar as informações de um projeto existente, como título, descrição, datas ou status. As alterações são enviadas em formato JSON para o servidor e atualizam os dados do projeto.

Remoção de Projetos: É possível excluir um projeto da lista, removendo-o do conjunto de dados no servidor.

Pesquisa de Projetos: Os usuários podem realizar pesquisas na lista de projetos com base em critérios específicos, como título, datas ou status. Os resultados são retornados em formato JSON.

### Tecnologias Utilizadas:
O projeto é desenvolvido em Java utilizando o framework Spring Boot, que fornece uma estrutura robusta para criação de aplicativos web. Para lidar com os dados em formato JSON.

## Como Execultar

## Pré-requisitos
- Java Development Kit (JDK) instalado na versão 17.
- Maven instalado ou configurado como dependência no projeto.

## Passos

1. Clone o repositório do projeto:

   ```shell
   git clone https://github.com/elvisthermo/gerenciador-projetos.git
   ```
   
2. Crie um arquivo .env na raiz do projeto com as seguintes variaveis: 
  
  ```env
     POSTGRES_USER=seu_user
     POSTGRES_PASSWORD=sua_senha
     POSTGRES_DB=nome_db
  ```
  

### Banco de dados 
Ao seguir esses passos, você poderá executar sua aplicação Spring Boot localmente, com o banco de dados PostgreSQL configurado. Certifique-se de ter todos os pré-requisitos instalados corretamente e o banco de dados PostgreSQL disponível, seja instalado localmente ou por meio do Docker, antes de iniciar o processo.


### Docker
 Crie um arquivo .env para o Docker na raiz do projeto com as seguintes variáveis: 
  
  ```env
     POSTGRES_USER=seu_user
     POSTGRES_PASSWORD=sua_senha
     POSTGRES_DB=nome_db
  ```
Dentro da pasta docker localizada na raiz do projeto execute o seguinte comando:
```shell
  docker compose-up
```

### Documentação

Para acessar a documentação swagger acesse:

http://localhost:8080/swagger-ui/index.html