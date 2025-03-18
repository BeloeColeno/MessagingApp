# Messaging App

## Overview
Это микросервисное приложение на SpringBoot. Разрабатваемое для курса по прикладному программированию.

## Requirements
- Java 23 or higher
- Maven 3.6.0 or higher
- MySQL 8 or higher

## Setup
1. Запуск базы данных в докер:
```
docker run -e
MYSQL_ROOT_PASSWORD=passw -d
--name bird -v bird-db-data:/var/folders/mysql/data -v
./database:/database -p
3306:3306 mysql:latest2
```
2. Сборка проекта предпологается по отдельности.
- каждый сервис нужно собирать в maven отдельным приложением.
3. Скрипт для инициализации базы данных есть в пректе.
```
../src/resources/BDScript.cs
```

## Usage
1. Endpoint 1 for User Service:[GET] /users
 - Получение списка всех пользователей
2. Endpoint 2 for User Service:[GET] /user/{id}
 - Получение одного пользователя по его id
3. Endpoint 3 for User Service:[POST] /user
 - Создание пользователя. Нужно передавать тело запроса в формате Json.
```
{
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
```
4.Endpoint 4 for User Service:[PUT] /user/{id}
 - Обновление пользователя по его id. Нужно передавать данные в формате Json.
```
{
  "name": "John Doe",
  "email": "John@Doe.com",
  "password": "John_Doe_pass_123",
  "roles": [
    {
      "role": "SUBSCRIBER"
    }
  ]
}
```
5. Endpoint 5 for User Service:[DELETE] /user/{id}
 - Удаление пользователя по его id
   
6. Endpoint 1 for Message Service:[GET] /message/{id}
 - Получение сообщения по id.
7. Endpoint 2 for Message Service:[GET] /producer/{id}/messages
 - Получение всех сообщеий одного автора по его id.
8. Endpoint 3 for Message Service:[GET] /subscriber/{id}/messages
 - Получение всех сообщений для одного подписчика по его id.
9. Endpoint 4 for Message Service:[POST] /message
 - Создание сообщения. Нужно передавать тело запроса в формате Json.
```
{
    "author": "6e27ea06-a716-4c89-af88-813749a8bd48",
    "content": "Does it write in?"
}
```
10. Endpoint 5 for Message Service:[PUT] /message/{id}
- Обновление сообщения по его id. Нужно передавать данные в формате Json.
```
{
    "author": "6e27ea06-a716-4c89-af88-813749a8bd48",
    "content": "Does it update?"
}
```
11. Endpoint 6 for Message Service:[DELETE] /message/{id}
 - Удаление сообщения по его id
