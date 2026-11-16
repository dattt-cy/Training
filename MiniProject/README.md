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

## Module 3: REST API cơ bản

- `Employee` (model): dữ liệu nhân viên, lưu trong bộ nhớ (in-memory), chưa dùng database
- `EmployeeService`: lưu danh sách nhân viên bằng `ArrayList`, có sẵn 3 nhân viên mẫu.
  Thêm mới thì tự tạo mã nhân viên và chuẩn hóa họ tên bằng `UtilityService` (Module 2)
- `EmployeeController`: dùng `@GetMapping`, `@PostMapping`, `@PathVariable`, `@RequestParam`,
  `@RequestBody`, `ResponseEntity`

| Method | URL | Kết quả |
|--------|-----|---------|
| GET | /api/employees | Danh sách nhân viên |
| GET | /api/employees?department=IT | Lọc theo phòng ban |
| GET | /api/employees/1 | 1 nhân viên, không có thì trả `404` |
| POST | /api/employees | Thêm nhân viên, trả `201`. Thiếu họ tên / phòng ban thì trả `400` |

Body mẫu cho POST:

```json
{
  "fullName": "phạm thị dung",
  "email": "dung@gmail.com",
  "department": "IT",
  "salary": 11000000
}
```

Dữ liệu chỉ nằm trong RAM, khởi động lại app sẽ quay về 3 nhân viên mẫu.
(Từ Module 4, API này được thay bằng phiên bản dùng database.)

## Module 4: Spring Boot + Database (Spring Data JPA)

- Cấu hình MySQL trong `application.properties`. Database `employee_management` và các bảng tự tạo khi chạy app
- Entity `Department` → bảng `department` (id, name)
- Entity `Employee` → bảng `employee` (id, code, full_name, email, salary, department_id)
- Quan hệ nhiều nhân viên thuộc 1 phòng ban: `@ManyToOne` + `@JoinColumn(name = "department_id")`
- `EmployeeRepository`, `DepartmentRepository` kế thừa `JpaRepository`
- CRUD nhân viên với database, tìm kiếm theo tên và/hoặc phòng ban
- `DataInitializer`: tạo 3 phòng ban và 4 nhân viên mẫu nếu database còn trống

| Method | URL | Kết quả |
|--------|-----|---------|
| GET | /api/employees | Danh sách nhân viên |
| GET | /api/employees?keyword=văn | Tìm theo tên |
| GET | /api/employees?departmentId=1 | Lọc theo phòng ban |
| GET | /api/employees?keyword=văn&departmentId=1 | Tìm theo tên + phòng ban |
| GET | /api/employees/1 | 1 nhân viên, không có thì `404` |
| POST | /api/employees | Thêm nhân viên, trả `201` |
| PUT | /api/employees/1 | Sửa nhân viên, trả `200` |
| DELETE | /api/employees/1 | Xóa nhân viên, trả `204` |
| GET | /api/departments | Danh sách phòng ban |
| GET | /api/departments/1 | 1 phòng ban |
| POST | /api/departments | Thêm phòng ban, body `{"name": "Marketing"}` |

Body mẫu cho POST / PUT nhân viên:

```json
{
  "fullName": "hoàng văn em",
  "email": "em@gmail.com",
  "salary": 14000000,
  "department": { "id": 1 }
}
```

## Chạy project

Cần bật MySQL trước (tài khoản cấu hình trong `src/main/resources/application.properties`).

```
mvnw.cmd spring-boot:run
```

Mở http://localhost:8080/hello, kết quả:

```
Hello, Employee Management System!
```
