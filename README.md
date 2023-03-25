# Innowise-Admin-User-Spring-Boot-Application

Employee tracking system divided on admins and usual users

## Task Requirements

Сортировка мячей:

Реализовать систему учета данных работников компаний.
Админ может: 
* редактировать 
* добавлять 
* удалять 
* просматривтаь информация о сотрудниках 

Cотрудник может: 

* просматривать

Пароли в базе данных хранить только в хэшированном виде, хэш-функцию выбирается любая.

Стек: **Java 11+**, **Spring(Core, Boot, MVC, Data, Security)**, **Lombok**, **MapStruct**, любая реляционная субд на ваш выбор.

Peculiarities:

* Due to misunderstanding, the task is implemented in two different variants - 1) Using Rest Controllers; 2) Using usual Spring MVC Controllers and Template engine Thymeleaf (package **web** is divided on two - **rest** and **mvc**, the second one is fully commented, in order to avoid compatibility issues);

* Since the application comes with empty Database, I implemented the following things: 1)In the root of the project you'll find **schema.sql** file, so that you have exact Database structure, that I used to develop the project; 2) In Security Configuration class I left commented code, that allows all users (whether it is admin or not) to add new data to Database - for convenience purpose; 

## Installation

You are proposed to use Java 11.

![This is an image](https://i.ibb.co/f1HC8RZ/image.png)

Current Maven version.

![This is an image](https://i.ibb.co/5M5bxcm/image.png)

While developing this project I used **PostgreSQL** Database.

There're the steps to unpack my project: 

* git clone Innowise-Admin-User-Spring-Boot-Application

* Open it via your IDE

## Usage

Run application from command line: 

* Open the folder in which you cloned the project

* javac AdminUserWebApplication.java 

* java AdminUserWebApplication

## API endpoints

1) http://localhost:8080/login **GET** - performs login
2) http://localhost:8080/logout **POST** - performs logout
3) http://localhost:8080/registration **POST** - registers new user
4) http://localhost:8080/userpool **GET** - shows all the users in Database
5) http://localhost:8080/userpool/user/{id} **GET** - shows concrete user
6) http://localhost:8080/userpool/update/{id} **PATCH** - changes concrete user data
7) http://localhost:8080/userpool/update/{id} **DELETE** - deletes concrete user

