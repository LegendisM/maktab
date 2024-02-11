# Maktab

### This project was created with Kotlin language and using Spring framework.

### The purpose of this project is to manage books, users, attachments and more (RestfulAPI).

### Dependencies
- Spring Framework
- Spring Boot
- Spring Boot Security
- Spring Data JPA
- Validation
- Postgres Driver
- Json Web Token (JWT)
- Map Struct

### Modules
- Auth Module (signin, signup, auth-interceptor, currentUser-annotation, etc)
- User Module
- Book Module (create, filter, findOne, update, delete, etc)
- Category Module (create, filter, findOne, update, delete, etc)
- Common (Pagination, General Responses DTO, General Exceptions, SecretConfiguration)

### TODO
- [ ] Resource Manager (S3)
  - [ ] Add user avatar-image
  - [ ] Add book avatar-image, pdf-file
- [ ] Policy Manager (user-permissions, user-role)
  - [ ] User Role
  - [ ] User Permissions
- [ ] Profile
  - [ ] Update user profile
- [ ] Vote
  - [ ] Add vote option for books (star)
- [ ] Migration - Flyway
- [ ] Docker
- [ ] Events (Publish, Subscription)