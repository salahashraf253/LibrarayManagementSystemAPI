# Library Management System API
## Introduction
<h3>This is a RESTful API for a Library Management System built using Spring Boot. It allows users to register as borrowers or admins and perform actions related to book management and borrowing. </h3>

## Features

<ul>
  <li>Authentication & Authorization (JWT-based Security)</li>
  <li>Role-based Access Control (Admin vs. Borrower Permissions)</li>
  <li>Book Management (Admin only)</li>
  <li>Borrowing System (Checkout/Return Books)</li>
  <li>Borrowing Reports (Admin only)</li>
</ul>

## API Documentation

### API Endpoints

#### Authentication API

| HTTP Verbs | Endpoints | Action |
| --- | --- | --- |
| POST | ```/api/auth/register/borrower``` | Register a new borrower |
| POST | ```/api/auth/register/admin```    | Register a new admin |
| POST | ```/api/auth/login``` | Login as an admin or borrower |

<h5>Authentication Details</h5>
<ul>
  <li>Upon successful login, users receive a JWT Token.</li>
  <li>The token must be included in the Authorization header as a Bearer Token in subsequent requests.</li>
  <li>Example<br>
    
    Authorization: Bearer your-jwt-token
  
  </li>
  <li>Endpoints marked as Admin only require an admin role to access.</li>
</ul>

#### Books API

| HTTP Verbs | Endpoints | Action |
| --- | --- | --- |
| GET | ```/api/books``` | Get all books(with filter) |
| GET | ```/api/books/{id}``` | Get a single book by ID | 
| POST | ```/api/books``` | Add a new book (Admin only) |
| PUT | ```/api/books/{id{``` | Update a book (Admin onlu) | 
| DELETE | ```/api/books/{id}``` | Delete a book (Admin only |

#### Borrowing API (For Borrowers)

| HTTP Verbs | Endpoints | Action |
| --- | --- | --- |
| GET | ```/api/borrowings/``` | Get borrowered books for the current borrower|
| POST | ```/api/borrowings/checkout``` | Borrow a book |
| POST | ```/api/borrowings/return``` | Return a borrowed book |

#### Borrowing Reports API (For Admins)

| HTTP Verbs | Endpoints | Action |
| --- | --- | --- |
| GET | ```/api/borrowings/overdue``` | Get overdue books |
| GET | ```/api/borrowings/active``` | Get active borrowings |
| GET | ```/api/borrowings/active/{borroerId}``` | Get active borrowings by borrower ID |
| GET | ```/api/borrowings/report?startDate={}&endDate={}``` | Generate a borrowing report |
| GET | ```/api/borrowings/export?startDate={}&endDate={}&format={}``` | Export borrowing reports |

### Technologies Used

<ul>
  <li>Spring Boot (Java)</li>
  <li>Spring Security (JWT Authentication & Authorization)</li>
  <li>Spring Data JPA (Database Access)</li>
  <li>MySQL (Database)</li>
  <li>Lombok (Reduces Boilerplate Code)</li>
  <li>SonarQube (Static Code Analysis & Quality Assurance)</li>
</ul>
