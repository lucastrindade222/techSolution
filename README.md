# TechSolution

Este projeto foi gerado com [start.spring](https://start.spring.io/).

Versão do Spring boot 2.6.12-SNAPSHOT
## Execução do projeto
Para executar o projeto deverá ter na sua máquina 
*  OpenJDK 11 ou JDK 11
* [Docker](https://docs.docker.com/engine/install/)
* [Docker Compose](https://docs.docker.com/compose/install/) 

 O banco escolhido para este projeto foi o [Mysql](https://www.mysql.com/).
 
### Execuçao do Banco
[Clone o projeto](https://docs.github.com/pt/repositories/creating-and-managing-repositories/cloning-a-repository) e abra o seu terminal no diretório raiz do projeto e digite o seginte comando:
```shell
 cd docker/mysql/  
```
Já dentro do diretório execute o seguinte comando: 

```shell
sudo docker-compose up
```
Para verificar se o docker está rodando corretamente execute o seguinte comando:
```shell
sudo docker ps
```

O retorno deverá ser parecido com este exemplo:

![docker ps](./img/dockerps.png)
O docker subiu duas imagens, mysql e  phpmyadmin.

Para utilização do [phpMyAdmin](https://www.phpmyadmin.net/) Navegue até `http://localhost:80/ ou http://localhost
e preencha os campos 
* Servidor:  db
* Utilizador : root
* Palavra-passe: frodobolson

![docker ps](./img/phpmy.png)
# ⬇️

![docker ps](./img/phpmy.png)
## Rotas

### Autenticação

A autenticação dos usários da plataforma devará ser realizada a seguir os exemplos abaixo:

#### POST localhost:8080/api/v1/login
```json
{

"email":"lucasdevjava@gmail.com",
"password":"123456"


}
```
#### Resposta

| KEY | VALUE |
|-----|-------|
|   Authorization  |  Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJsdWNhc0BnbWFpbC5jb20iLCJpZCI6MSwiZW1haWwiOiJsdWNhc0BnbWFpbC5jb20iLCJyb2xlIjpbeyJub21lUm9sZSI6IlJPTEVfQURNSU4iLCJhdXRob3JpdHkiOiJST0xFX0FETUlOIn1dLCJpYXQiOjE2NjI2ODcyNDYsImV4cCI6MTY2Mjc3MzY0Nn0.0buLyplX2zRJwi-XbY3buYyLCCEvVlrAF_Uloqw8yUnQKGLikCdXMDpEqxRQDMKbisKF5pr7rJUwbEOBaRAYrA     |



### Autorização

Para fazer uma autorização deve passar no cabeçalho da requisição: 




 | KEY | VALUE |
|-----|-------|
|   Authorization  |  Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJsdWNhc0BnbWFpbC5jb20iLCJpZCI6MSwiZW1haWwiOiJsdWNhc0BnbWFpbC5jb20iLCJyb2xlIjpbeyJub21lUm9sZSI6IlJPTEVfQURNSU4iLCJhdXRob3JpdHkiOiJST0xFX0FETUlOIn1dLCJpYXQiOjE2NjI2ODcyNDYsImV4cCI6MTY2Mjc3MzY0Nn0.0buLyplX2zRJwi-XbY3buYyLCCEvVlrAF_Uloqw8yUnQKGLikCdXMDpEqxRQDMKbisKF5pr7rJUwbEOBaRAYrA     |



### Sobre Admin
O administrador e o usário com a maior hierarquia no sistema.

O sistema cria automaticamente um administrador, para realizar a criação de outro usário com as permissões de administrador é necessário passar a autenticação: 


### Criação de admin

#### POST localhost:8080/api/v1/admin

```json
{

     "name":"Eru",
     "familyName":"Iluvatar",
     "password":"Chama_de_Anor",
     "email":"iluvatar@gmail.com"



}
```

### Listar todos os admin 
Para listar todos os administradores o usário deve estar autenticado como adminstrador.

#### GET localhost:8080/api/v1/admin

#### Buscar pelo Token
Para buscar o administrador pelo token deve se passar o token na seguinte rota: 

##### GET localhost:8080/api/v1/admin/searchMe

### Sobre Cliente
O cliente é um usario com uma hierarquia a baixo do administrador.
Para a criação de um cliente não é necessario autorização no sistema.

### Criação de cliente
#### POST localhost:8080/api/v1/client
```json
{

  "name":"lucas",
  "lastName":"Trindade da Silva",
  "password":"123456",
  "email":"luva@gmail.com"



}
```
### Sobre os Produtos
Os produtos só podem ser cadastrados e deletados pelo administrador.
A visualização dos produtos pode ser feito pelo administrador ou cliente.


### Criar Produto
#### POST localhost:8080/api/v1/products
```json
{

    "productname":"casa",
    "provider":"americanas",
    "productValue":"1100,03"


}
```
### Listar todos os produtos cadastrados

#### GET localhost:8080/api/v1/products

#### Apagar o produto pelo Id
Caso seja necessário pode-se apagar o produto cadastrado.
O Id do produto e pássado por parâmetro na requisição como o exemplo abaixo:
#### GET localhost:8080/api/v1/products?id=1
