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
- [x] User Profile Management
  - [x] Add `PATCH /profiles/me` for updating user profile (username, avatar)
- [x] Policy Manager (user-permissions, user-role)
  - [x] User Role
  - [x] User Permissions
  - [x] Initializer (default roles and permissions)
  - [x] Interceptor + Annotations (Guard) to check permissions and roles in controllers
- [x] Tag Module
- [ ] Campaign Module
  - [x] Manage campaign (create, find, delete)
    - [x] Validate ownership policy for update/delete operation
  - [x] Join/Leave from campaign (campaign members)
  - [x] Campaign member management (campaign members)
- [ ] Notation Module (TWT)
  - [ ] Implement (entity, service, models, etc)
- [ ] Vote
  - [ ] Add vote option for books (star)
- [ ] Purchasable products (book, etc)
- [ ] Migration - Flyway
- [ ] Docker
- [ ] Events (Publish, Subscription)