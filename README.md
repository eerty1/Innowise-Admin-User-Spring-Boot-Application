# Innowise-Admin-User-Spring-Boot-Application

Employee tracking system divided on admins and usual users

## Task Requirements

Implement a data accounting system for company employees
Admin can: 
* Edit employee data
* Add new employees
* Delete existing employee
* Read employee data

Employee can: 

* Read employee data

Passwords in the database should be stored only in hashed form, any hash function could be used.

Stack: **Java 11+**, **Spring(Core, Boot, MVC, Data, Security)**, **Lombok**, **MapStruct**, any relational database.

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

2) http://localhost:8080/logout **GET** - performs logout

3) http://localhost:8080/registration **POST** - registers new user

4) http://localhost:8080/userpool **GET** - shows all the users in database

5) http://localhost:8080/userpool/get/{id} **GET** - shows concrete user

6) http://localhost:8080/userpool/update/{id} **PATCH** - updates concrete user data

7) http://localhost:8080/userpool/delete/{id} **DELETE** - deletes concrete user