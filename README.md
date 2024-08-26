# Ecommerce website with microservice architecture

# Preview

Architecture of the app:

<p align="center">
    <img src="assets/architecture_ecommerce.png" alt="Microservice architecture"/>
</p>

# Table of content

- [Technologies](#technologies)
- [Design](#design)
- [Microservice design patterns](#microservice-design-patterns)
- [Contributeurs](#contributeurs)

# Technologies

**Framework:** Spring Boot <img alt="Spring" width="30px" style="padding-right:10px;" src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/spring/spring-original.svg" /> (
  Maven <img alt="Maven" width="30px" style="padding-right:10px;" src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/maven/maven-original.svg" />,
  Java 17 <img alt="Java" width="30px" style="padding-right:10px;" src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/java/java-original.svg" />)

**Server:** Apache web server <img alt="Apache" width="30px" style="padding-right:10px;" src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/apache/apache-original.svg" />

**Database:** PostgreSQL <img alt="PostgreSQL" width="30px" style="padding-right:10px;" src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/postgresql/postgresql-original.svg" />

**HTTP Request tests:** Postman PostgreSQL <img alt="Postman" width="30px" style="padding-right:10px;" src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/postman/postman-original.svg" />

# Design

## Class diagram

From an initial class diagram, we divided our app into different services:

<p align="center">
    <img src="assets/diag_class_decomposition.png" alt="Class diagram"/>
</p>

# Microservice design patterns

## Database Per Service

We used containerized PostgreSQL databases for each service to ensure data encapsulation and independant scaling. This allows each service to be built, deployed and scaled along with its own database independently. 

<p align="center">
    <img src="assets/database_per_service.png" alt="Database per service illustration"/>
</p>

## Service Registration and Discovery

The service register acts as a centralized directory containing the location of each existing service. Whenever a new service is deployed, it has to subscribe first to the service registry by sending its IP adress and port number. Other services or clients can then query the service registry to find and communicate with these services dynamically.

<p align="center">
    <img src="assets/service_registry.png" alt="Service registry"/>
</p>

**Eureka** is a service registry and discovery tool developed by Netflix, and it is part of the Netflix OSS stack. 

<p align="center">
    <img src="assets/eureka.png" alt="Netflix Eureka"/>
</p>

## API Gateway

## Distributed tracing

## Message queues

# Contributeurs

- [@Cristal32](https://github.com/Cristal32)
- [@fatibr19](https://github.com/fatibr19)
