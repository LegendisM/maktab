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
- AWS S3 (io.awspring.cloud)

### Modules
- Auth Module (signin, signup, auth-interceptor, currentUser-annotation, etc)
- User Module
- Book Module (create, filter, findOne, update, delete, etc)
- Category Module (create, filter, findOne, update, delete, etc)
- Common (Pagination, General Responses DTO, General Exceptions, SecretConfiguration)
- Storage Module (S3)

### TODO
- [x] Storage Module (S3)
  - [x] Add user avatar-image
  - [x] Add book images, document-file
  - [x] MultipartFile Validator (content-type, size, etc)
- [ ] Policy Manager (user-permissions, user-role)
  - [ ] User Role
  - [ ] User Permissions
- [ ] Profile
  - [ ] Update user profile
- [ ] Book Document (Core)
- [ ] Vote
  - [ ] Add vote option for books (star)
- [ ] Migration - Flyway
- [ ] Docker
- [ ] Events (Publish, Subscription)