# BandBook REST API

[Privides data for BandBook app]

# Comment API Endpoints

## Overview

This document describes the endpoints of the Comment API in the application. The API allows for creating, retrieving, updating, and deleting comments.

Base URL: `/api/v1/comment`

## Endpoints

### 1. Create a Comment

- **URL**: `/api/v1/comment`
- **Method**: `POST`
- **Body**:
  - `CommentRequest` object
- **Response**:
  - `201 Created` on success
  - `400 BadRequest` with error message on failure
- **Description**:
  Creates a new comment based on the provided `CommentRequest` data.

### 2. Get All Comments

- **URL**: `/api/v1/comment`
- **Method**: `GET`
- **Response**:
  - Array of `CommentResponse` objects
- **Description**:
  Retrieves a list of all comments.

### 3. Get Comment by ID

- **URL**: `/api/v1/comment/{id}`
- **Method**: `GET`
- **URL Parameters**:
  - `id` [Integer]: The ID of the comment to retrieve.
- **Response**:
  - `CommentResponse` object on success
  - `400 BadRequest` with error message on failure
- **Description**:
  Retrieves a specific comment by its ID.

### 4. Update Comment by ID

- **URL**: `/api/v1/comment/{id}`
- **Method**: `PATCH`
- **URL Parameters**:
  - `id` [Integer]: The ID of the comment to update.
- **Body**:
  - `CommentRequest` object
- **Response**:
  - `204 NoContent` on success
  - `400 BadRequest` with error message on failure
- **Description**:
  Updates the content of an existing comment identified by its ID.

### 5. Delete Comment by ID

- **URL**: `/api/v1/comment/{id}`
- **Method**: `DELETE`
- **URL Parameters**:
  - `id` [Integer]: The ID of the comment to delete.
- **Response**:
  - `204 NoContent` on successful deletion
  - `400 BadRequest` with error message on failure
- **Description**:
  Deletes a comment identified by its ID.

## Models

### CommentRequest

- Structure of the request body used for creating or updating comments.

### CommentResponse

- Structure of the response returned when fetching comments.



