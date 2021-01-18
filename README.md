# Enrollment Tracker APP
This app showcases a bunch of different functionalities with respect to Enrolling/Updating/Fetching/Deleting Enrollees and its associated Dependents.

## Implementation Details
This application uses Spring JPA ORM to interact the Database (MySQL), mainly with Enrollee & Dependent Entities


## Enrollment Tracker APP
This APP showcases CRUD Operations for tracking the enrollment of Enrollees & Dependents using RESTFul Micro-services.

## Motivation
This APP was developed as part of application of Spring Boot Micro-services exercise

## Build status
Not Applicable, Plain local build support using maven. 

## Features
Micro-service we developed in Spring Framework, which include multiple features as follows:
1. Clear representation of GET/POST/PUT/DELETE Methods in RestFul Microservices API.
2. JPA based implementation (MySQL).
3. tomcat based connection pooling, using application properties.
3. Swagger api-docs Implemenation.
4. Global Exception handler.
5. Securing the Passwords (one of the ways) using Jasypt package.

## Code Example
Show what the library does as concisely as possible, developers should be able to figure out **how** your project solves their problem by looking at the code example. Make sure the API you are showing off is obvious, and that your code is short and concise.

## Installation
Run the DB scripts from resources folder, update the DB detatils and run the app.
spring-boot:run

## API Reference
After bringing up the App, api-docs will be available at the following end-point
http{s}://host:{port}/v2/api-docs?group=enrollemnt-tracker
