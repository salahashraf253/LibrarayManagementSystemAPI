# Library Management System API
## Introduction
### This is a RESTful API for a Library Management System built using Spring Boot. It allows users to register as borrowers or admins and perform actions related to book management and borrowing. 
## Entity Relationship Diagram (ERD)
![ERD](https://github.com/salahashraf253/LibrarayMangementSystemAPI/blob/master/ERD.png)
## Features

- Authentication & Authorization (JWT-based Security)
- Role-based Access Control (Admin vs. Borrower Permissions)
- Book Management (Admin only)
- Borrowing System (Checkout/Return Books)
- Borrowing Reports (Admin only)
- Code Quality & Analysis (SonarQube)
- Rate Limiting (Prevent excessive API requests)
- Extra Data in API Responses (Optional metadata for debugging & analytics)
- Swagger API Documentation (Interactive API exploration)
- Docker (Containerized deployment)


## API Documentation

### API Endpoints

#### Authentication API

| HTTP Verbs | Endpoints | Action |
| --- | --- | --- |
| POST | ```/api/auth/register/borrower``` | Register a new borrower |
| POST | ```/api/auth/register/admin```    | Register a new admin |
| POST | ```/api/auth/login``` | Login as an admin or borrower |

##### Authentication Details
- Upon successful login, users receive a JWT Token.
- The token must be included in the Authorization header as a Bearer Token in subsequent requests.
- Example:
    `
   Authorization: Bearer your-jwt-token`
  
  
- Endpoints marked as Admin only require an admin role to access.</li>


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
| ---- | ---- | ---- |
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
<li>Swagger UI (API Documentation & Testing)</li>
    <li>Docker (Containerized  Deployment)</li>
</ul>

### Rate Limiting

<p>
  This project includes Rate Limiting to prevent excessive API requests. A custom annotation @RateLimited is used to enforce request limits per endpoint.

Example Usage:
```java
    @RateLimited
    @GetMapping()
    public ResponseEntity<ApiResponse<BookFetchResponse>> fetchBooks(
            HttpServletRequest httpRequest,
            @RequestParam(defaultValue = "false") boolean includeExtraData,
            @ModelAttribute BookFetchRequest bookFetchRequest
    ) {
        BookFetchResponse response = bookService.findAll(bookFetchRequest);

        return extraDataUtil.buildResponse(httpRequest, includeExtraData, response, HttpStatus.OK);
    }
```
</p>

### Extra Data in API Responses

This project includes an Extra Data Utility that allows API responses to include additional metadata when requested.
##### How it Works
<!-- <ul> -->
- Users can pass `?includeExtraData=true` in API requests 
- Extra metadata includes:
  <ul>
    <li>TImestamp</li>
    <li>Response size</li>
    <li>Request ID</li>
    <li>Server Info</li>
    <li>API version</li>
    <li>Client IP address</li>
    <li>User-Agent</li>
  </ul>
  </li>

<!-- </ul> -->
Example Response with Extra Data:

```json
{
  "status": 200,
  "data": {
    "id": 1,
    "title": "Clean Code",
    "Author":"Robert Cecil Martin"
    "quantity":20
  },
  "extraData": {
    "timestamp": "2025-03-19T12:34:56Z",
    "responseSize": 256,
    "requestId": "abc-123",
    "serverInfo": "Library API Server v1.0",
    "clientIp": "192.168.1.1",
    "apiVersion": "v1",
    "userAgent": "PostmanRuntime/7.29.0"
  }
}
```

### Swagger API Documentation
Swagger UI is available for interactive API testing and documentation.
#### Acessing Swagger UI
- Open a browser and navigate to:
      `http://localhost:8080/swagger-ui.html`
- Explore and test API endpoints directly from the interface.
 - OpenAPI JSON documentation can be accessed at:
    `http://localhost:8080/v3/api-docs`
    
