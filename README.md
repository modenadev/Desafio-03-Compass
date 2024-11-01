# Desafio-03: API de Usuários com Spring Boot

# Introdução

Este projeto consiste em uma API desenvolvida com Spring Boot que permite o registro de usuários e a atualização de senhas, implementando segurança com JWT e um sistema de mensageria utilizando Kafka. Além disso, a API integra-se à ViaCEP para buscar endereços a partir de CEPs fornecidos durante o cadastro.

Funcionalidades
1. Registro de Usuário
Endpoint: POST /api/users/register

Descrição: Permite o registro de um novo usuário.

Request Body:

json
{
 
  "username": "string",

 "password": "string",
 
  "email": "string",
  
  "cep": "string"

}


Response Body:

json

{

  "username": "string",
 
  "email": "string",

  "address": {
  
    "zipCode": "12345678",
  
    "street": "Example Street",
  
    "complement": "Apt 101",
   
    "neighborhood": "Example Neighborhood",
   
    "city": "Example City",
   
    "state": "SP"

  }

}

Implementações:

A senha é criptografada antes de ser armazenada no banco de dados.

A API consulta o endereço completo utilizando o CEP fornecido, através da API ViaCEP, e salva essas informações junto com o usuário.

2. Atualização de Senha

Endpoint: PUT /api/users/update-password

Descrição: Permite que usuários autenticados atualizem sua senha.

Request Body:

json

{

  "username": "string",
 
  "oldPassword": "string",
  
  "newPassword": "string"

}

Response: Sem conteúdo de resposta (204 NO CONTENT).

Implementações:

Valida a senha antiga e atualiza para a nova senha caso a validação seja bem-sucedida.

Segurança com JWT

A API utiliza Spring Security para autenticação e autorização através de tokens JWT.

O endpoint de registro de usuário é acessível a todos, enquanto o endpoint de atualização de senha é restrito a usuários autenticados.

Mensageria com Kafka

O sistema de mensageria foi implementado utilizando Kafka.

Após o registro ou atualização de um usuário, uma mensagem é enviada a um microserviço "notify" contendo:

{ 

    "message": "Sua mensagem aqui"

    
}


O microserviço "notify" é responsável por receber a mensagem e imprimi-la no console.

Microserviço Notify

Recebe mensagens da fila Notify do RabbitMQ.

Imprime no console as mensagens que contêm a message.

Exemplo de mensagem recebida:

yaml

message: "Mensagem Teste"

Integração com ViaCEP

Durante o registro do usuário, a API faz uma chamada à API ViaCEP para buscar o endereço completo a partir do CEP fornecido. Os dados de endereço são salvos junto ao usuário.

Docker Compose

Para facilitar a configuração do ambiente, um arquivo docker-compose.yml foi criado para orquestrar o Kafka e o Zookeeper, tornando mais simples o desenvolvimento e a correção do projeto.

Como Executar o Projeto

Clone o repositório:



git clone https://github.com/modenadev/Desafio-03-Compass.git
cd Desafio-03-Compass/'User-Project - Main'
Suba os serviços do Docker:

docker-compose up

Inicie o container Docker

Execute a classe principal do projeto.

Conclusão

Este projeto demonstra a implementação de uma API segura e eficiente utilizando Spring Boot, com integração a serviços externos e uma arquitetura baseada em microserviços. As práticas de segurança e a utilização de mensageria tornam a aplicação escalável e robusta.
