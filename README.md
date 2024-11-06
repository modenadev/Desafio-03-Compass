## Challenge 03

This repository contains an API developed with Spring Boot that allows user registration, password updates, and communication with other microservices through messaging using RabbitMQ.

In addition, the API implements security with JWT and integrates with the ViaCEP API to retrieve address data from the zip code provided at the time of registration.

## Challenge Objectives

User Registration: Endpoint to register users, with password encryption and integration with the ViaCEP API.

Password Update: Endpoint to update the password of an authenticated user.

Security: Implementation of authentication and authorization using JWT tokens.

Messaging: Sending messages to an external microservice (called "notify") using Kafka or RabbitMQ.

Integration with ViaCEP: Obtaining address information through the zip code provided by the user at the time of registration.

## Project Structure

Spring Boot for creating the REST API.

Spring Security for authentication and authorization management.

JWT for token generation and validation.

RabbitMQ for sending messages between microservices.

Docker Compose for orchestrating the execution of Kafka and Zookeeper containers.

ViaCEP API for retrieving address information based on the provided zip code.

## Functionalities
1. User Registration

Endpoint: POST /api/users/register

This endpoint receives the user's registration data, including a zip code. The API performs the following steps:

Password validation and encryption.

Call to the ViaCEP API to retrieve the address corresponding to the provided zip code.

User registration in the database with the information received and the address obtained.

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

## 2. Password Update
Endpoint: PUT /api/users/update-password

This endpoint allows an authenticated user to update their password. The old password is checked before being replaced by the new one.

Request Body:

json
{

"username": "string",

"oldPassword": "string",

"newPassword": "string"
}

Response: No Content (204) - Status 204 indicates that the operation was successful and that there is no content to be returned.

NOTE: The oldPassword must be the encrypted password in the database.

## 3. Authentication
Endpoint: POST /api/users/login

This endpoint authenticate a user.

{
"username": "string"
"password": "string"

}

Once authenticated, a token will be passed to access the other endpoints as a password update.

## How to run

"git clone https://github.com/modenadev/Desafio-03-Compass.git"

To clone the repository on your computer,

once that's done, open the Windows terminal and use the command 

cd C:\Users\yourusername\locationwherethefolderis\Desafio-03\User-Project - Main.

## Docker
Once that's done, just type docker-compose up and the application container will start running.

## Dependencies

Spring Boot: Main framework for building the API.

Spring Security: Implementation of authentication and authorization.

Spring Kafka / Spring AMQP: To send messages to the notification microservice.

JWT: To generate and validate authentication tokens.

ViaCEP API: To query addresses based on the zip code.

MySQL: Database for persisting user data.
