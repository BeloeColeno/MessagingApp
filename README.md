# Messaging App

## Overview
Это микросервисное приложение на SpringBoot. Разрабатваемое для курса по прикладному программированию.

## Requirements
- Java 23 or higher
- Maven 3.6.0 or higher
- MySQL

## Setup
1. Запуск базы данных в докер:
- docker run -e MYSQL_ROOT_PASSWORD=passw -d --name bird -v bird-db-data:/var/folders/mysql/data -v ./database:/database -p 3306:3306 mysql:latest2
2. Сборка проекта предпологается по отдельности.
- каждый сервис нужно собирать в maven отдельным приложением.
3. Скрипт для инициализации базы данных есть в пректе.
- ../src/resources/BDScript.cs

## Usage
1. Endpoint 1 for User Service:[GET] /users
 - Получение списка всех пользователей
2. Endpoint 2 for User Service:[GET] /user/{id}
 - Получение одного пользователя по его id
3. Endpoint 3 for User Service:[POST] /user
 - Создание пользователя. Нужно передавать тело запроса в формате Json[^1].
[1]: {
  "name": "John Doe",
  "email": "John@Doe.com",
  "password": "John_Doe_pass_123",
  "roles": [
    {
      "role": "SUBSCRIBER"
    },
    {
      "role": "PRODUCER"
    }
  ]
}
4. Endpoint 4 for User Service:[DELETE] /user/{id}
 - Удаление пользователя по его id
5. Endpoint 5 for User Service:[PUT] /user/{id}
 - Обновление пользователя по его id. Нужно передавать данные в формате Json[^2].
[2]:{
  "name": "John Doe",
  "email": "John@Doe.com",
  "password": "John_Doe_pass_123",
  "roles": [
    {
      "role": "SUBSCRIBER"
    }
  ]
}
