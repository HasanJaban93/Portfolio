# Task Manager Application

## Overview

The Task Manager Application is a full-featured task management system built using Java 21 and Spring Boot. It integrates JPA/Hibernate for database operations, allowing users to create, read, update, and delete tasks stored in a MySQL database.

## Features

- **Task Management**: Create, edit, delete, and view tasks.

- ## Technologies Used

- **Backend**: Java 21, Spring Boot, Spring Web, JUnit, JPA/Hibernate
- **Frontend**: HTML, CSS, JavaScript
- **Database**: MySQL

## Dependencies

### Developer Tools
- **Spring Boot DevTools**: Speeds up the development process by enabling quick restarts of only the changed parts of the application without needing a full restart.

### Web
- **Spring Web**: Provides an embedded Tomcat server and the necessary libraries to create web applications.

### SQL
- **Spring Data JPA**: An ORM library that helps persist objects as records in database tables and read records as objects.
- **MySQL Driver**: JDBC driver for MySQL.

### I/O
- **Validation**: Validates data to ensure it meets certain criteria.

## Configuration

Create the database `tasksManagerDB` in your MySQL server.

## Testing
You can find the unit tests for this application in the `src/test/java/be/myportfolio/tasks/TaskControllerTest.java` file. These tests cover various aspects of the Task Controller, ensuring that the application behaves as expected.

## Usage Instructions

### Creating a New Task

- **Input**: Provide a task description, a date in the present or future, and a time.
- **Notification**: You will receive a success or failure message indicating the reason for failure.
- **Uniqueness**: Each task must have a unique combination of date and time.

### Reading Tasks

You can retrieve tasks using the following methods:
- **Get all tasks**: Retrieve all tasks.
- **Get tasks by a word**: Retrieve tasks containing a specific word.
- **Get tasks by a date**: Retrieve tasks for a specific date.
- **Get tasks in a period**: Retrieve tasks within a date range.
- **Get tasks by state**: Retrieve tasks based on their state (completed or incomplete).

Tasks are displayed in a table format.

### Updating a Task

- Click the **Edit** button in the actions column of the task you want to update.
- Modify the attributes as needed and click **Save**.
- **Notification**: You will receive a success or failure message indicating the reason for failure.

### Deleting a Task

- Click the **Delete** button in the actions column of the task you want to delete.
- **Notification**: You will receive a success or failure message indicating the reason for failure.


Thank you for visiting my portfolio and using this application! Your feedback and suggestions are highly appreciated.
