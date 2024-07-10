# LibraSphere: Comprehensive Library Management System

LibraSphere is a sophisticated library management system designed to streamline and automate various operations within a library. It combines a powerful backend with a user-friendly frontend to deliver a seamless experience for both librarians and members.

## Key Features

### Frontend Development

- **Technologies Used:** HTML, CSS, JavaScript
- **Features:** Responsive and interactive interface for managing library operations.

### Backend Development

- **Technologies Used:** Java, Spring Boot
- **Spring Boot Dependencies:** Spring Boot DevTools, Spring Web, Spring Data JPA, MySQL Driver, Validation
- **Custom Validation:** Implemented custom bean validation for ISBN using Jakarta Validation API.

### Database Design

- **Database:** MySQL
- **Tables:** Authors, Awards, Members, Books, BooksAuthors, Copies, BorrowedCopies
- **Relationships:** One-to-many, many-to-many associations to maintain data integrity and model real-world library relationships.

### Key Concepts Implemented

- **Inheritance:** Used in the Copy class to differentiate between physical and digital copies.
- **Value Objects:** Such as Address for member details.
- **Associations:** Between entities like authors and books, books and copies, members and borrowed copies.
- **N+1 Problem:** Solved using optimized queries and fetching strategies.

### Database Schema

- **Authors:** Stores information about authors including biography, nationality, and awards.
- **Awards:** Linked to authors to keep track of awards won by them.
- **Members:** Holds details about library members including contact information and address.
- **Books:** Contains details about books in the library including ISBN validation.
- **BooksAuthors:** Maps the many-to-many relationship between books and authors.
- **Copies:** Differentiates between physical and digital copies of books.
- **BorrowedCopies:** Manages the borrowing and returning of book copies by members.

### Custom ISBN Validation

The custom ISBN validator ensures that both ISBN-10 and ISBN-13 formats are correctly validated. This is implemented using the Jakarta Validation API with a custom validator class (`IsbnValidator`) and annotation (`ISBN`).

## Getting Started

To get a local copy up and running follow these simple steps.

### Prerequisites

- Java JDK 8 or higher
- MySQL Server
- Maven
