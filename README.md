
# Book management API
Service allows users to manage a collection of books. You can add, view and delete the book.

## Endpoints
Below endpoints are implemented

| Endpoint             | HTTP Method | Query Parameters    | Optional Parameters | Description                                                                                                          |
|----------------------|-------------|---------------------|---------------------|----------------------------------------------------------------------------------------------------------------------|
| /api/books           | GET         |                     | N/A                 | Returns a list of all books in the collection                                                                     |
| /api/books/{id}      | GET         | id                  | N/A                 | Returns details of a specific book with the given ID.                                                                                                |
| /api/books           | POST        |                     | N/A                 | Creates a new book in the collection.                                                                                      |
| /api/books/{id}      | PUT         | id                  | N/A                 | Updates an existing book with the given ID.                                                                                            |
| /api/books/{id}      | DELETE      | id                  | N/A                 | Deletes a book with the given ID                                                                                                 |

## Request body for post request

### POST /api/books
The request body to create a new book

Example request body:
```json
{
    "title": "Introduction to JAVA and Data structure",
    "author": "Y. Liang",
    "publicationDate": "2021-10-10",
    "description": "A fundamentals-first introduction to basic programming concepts and techniques. Designed to support an introductory programming course, Introduction to Java Programming"
}

```

Example response body:
```json
{
    "id": 1,
    "title": "Introduction to JAVA and Data structure",
    "author": "Y. Liang",
    "publicationDate": "2021-10-10",
    "description": "A fundamentals-first introduction to basic programming concepts and techniques. Designed to support an introductory programming course, Introduction to Java Programming"
}

```

### GET /api/books/{id}
Example response body: Same as POST response

## Request body for put request

### PUT /api/books/{id}
Example request body: Same as POST request
Example response body: Same as POST response

### DELETE /api/books/{id}
No content on successfully deleted
