# Messaging App

## Overview
Это микросервисное приложение на SpringBoot. Разрабатваемое для курса по прикладному программированию.

## Requirements
- Java 23 or higher
- Maven 3.6.0 or higher
- MySQL

- ## Setup
1. Запуск базы данных в докер:
- docker run -e MYSQL_ROOT_PASSWORD=passw -d --name bird -v bird-db-data:/var/folders/mysql/data -v ./database:/database -p 3306:3306 mysql:latest2
2. Сборка проекта предпологается по отдельности - каждый сервис нужно собирать в maven отдельным приложением.

## Usage
- Endpoint for User Service 1: /users
