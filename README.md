# Emotibook Backend API

Welcome to the backend API for Emotibook - a sentiment-based social network.

## Overview

This Spring Boot project provides the backend functionality for managing posts, users, and ratings within the Emotibook application. It exposes RESTful endpoints to perform CRUD operations on the following entities:

- Post: Represents posts made by users.
- User: Represents users of the Emotibook application.
- Rating: Represents sentiment ratings provided by users for posts.

## Technologies Used

- Java
- Spring Boot
- Spring Data JPA
- Hibernate
- MySQL
- Maven

## Setup Instructions

1. Clone the repository to your local machine:

    ```bash
    git clone <repository-url>
    ```

2. Navigate to the project directory:

    ```bash
    cd emotibook-backend
    ```

3. Build the project using Maven:

    ```bash
    mvn clean install
    ```

4. Configure the application properties:

    - Rename `application-example.properties` to `application.properties`.
    - Update the database connection properties (`spring.datasource.url`, `spring.datasource.username`, `spring.datasource.password`, etc.) as per your environment.
    - Ensure that your MySQL server is up and running.

5. Run the application:

    ```bash
    mvn spring-boot:run
    ```

6. The backend API will be accessible at `http://localhost:8080`.

## API Endpoints

The following endpoints are available in the Emotibook API:

- **POST /posts**: Create a new post.
- **GET /posts**: Get all posts.
- **GET /posts/{postId}**: Get a post by ID.
- **PUT /posts/{postId}**: Update a post by ID.
- **DELETE /posts/{postId}**: Delete a post by ID.

- **POST /users**: Create a new user.
- **GET /users**: Get all users.
- **GET /users/{userId}**: Get a user by ID.
- **PUT /users/{userId}**: Update a user by ID.
- **DELETE /users/{userId}**: Delete a user by ID.

- **POST /ratings**: Create a new rating.
- **GET /ratings**: Get all ratings.
- **GET /ratings/{ratingId}**: Get a rating by ID.
- **PUT /ratings/{ratingId}**: Update a rating by ID.
- **DELETE /ratings/{ratingId}**: Delete a rating by ID.
