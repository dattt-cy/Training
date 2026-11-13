# Employee Management System

Mini project học Spring Boot.

## Module 1: Getting Started with Spring Boot

- Tạo project `employee-management` (Spring Boot 3, Java 17, Spring Web)
- Viết API `GET /hello` để kiểm tra project chạy thành công

## Module 2: Custom Bean & IoC

- `UtilityService` (`@Service`): chuẩn hóa họ tên, tạo mã nhân viên tự động (`NV0001`, `NV0002`, ...)
- `AppConfig` (`@Configuration`): định nghĩa bean `PasswordEncoder` bằng `@Bean`
- `UtilityController`: nhận 2 bean trên qua Constructor Injection

| Method | URL | Kết quả |
|--------|-----|---------|
| GET | /api/utility/format-name?name=nguyễn văn an | `Nguyễn Văn An` |
| GET | /api/utility/employee-code | `NV0001`, gọi lại ra `NV0002`, ... |
| GET | /api/utility/encode-password?password=123456 | mật khẩu đã mã hóa BCrypt |

## Chạy project

```
mvnw.cmd spring-boot:run
```

Mở http://localhost:8080/hello, kết quả:

```
Hello, Employee Management System!
```
