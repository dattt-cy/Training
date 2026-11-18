# Employee Management System

Mini project học Spring Boot: quản lý nhân viên, phòng ban, đăng nhập và phân quyền, thống kê.

**Công nghệ:** Java 17 · Spring Boot 3 · Spring Data JPA · Spring Security (form login, Basic Auth, JWT) · Thymeleaf · MySQL

## Chức năng

- Quản lý nhân viên: xem danh sách, thêm, sửa, xóa, tìm kiếm theo tên / phòng ban
- Đăng ký, đăng nhập (trang web và REST API)
- Phân quyền: **USER** chỉ được xem, **ADMIN** được thêm / sửa / xóa
- Thống kê số nhân viên theo phòng ban, tổng số nhân viên, lương trung bình

## Chạy project

1. Bật MySQL. Tài khoản cấu hình trong `src/main/resources/application-dev.yml`
   (database `employee_management` tự tạo khi chạy app).
2. Chạy:

   ```
   mvnw.cmd spring-boot:run
   ```

3. Mở http://localhost:8080

| Tài khoản | Mật khẩu | Quyền |
|---|---|---|
| admin | admin123 | ADMIN |
| user | user123 | USER |

## Trang web

| URL | Chức năng |
|---|---|
| /login, /register | Đăng nhập, đăng ký |
| /employees/list | Danh sách nhân viên |
| /employees/add | Thêm nhân viên (ADMIN) |
| /employees/search | Tìm kiếm theo tên / phòng ban |
| /employees/statistics | Thống kê |

## REST API

Đăng nhập lấy token rồi gửi kèm header `Authorization: Bearer <token>` (hoặc dùng Basic Auth).

| Method | URL | Quyền |
|---|---|---|
| POST | /api/auth/register | Ai cũng gọi được |
| POST | /api/auth/login | Ai cũng gọi được |
| GET | /api/employees?keyword=&departmentId= | USER, ADMIN |
| GET | /api/employees/{id} | USER, ADMIN |
| POST / PUT / DELETE | /api/employees, /api/employees/{id} | ADMIN |
| GET | /api/departments | USER, ADMIN |
| GET | /api/statistics, /api/statistics/total, /api/statistics/by-department | USER, ADMIN |
| GET | /api/reports/employees (cache 1 phút) | USER, ADMIN |
| GET | /actuator/health | Ai cũng gọi được |

## Cấu trúc

```
src/main/java/com/example/ems
├── controller   Controller trang web và REST API
├── service      Xử lý logic
├── repository   Truy vấn database (JpaRepository, @Query)
├── entity       Employee, Department, User, Role
├── dto          Dữ liệu vào / ra của API
├── security     Cấu hình Spring Security, JWT
├── exception    Xử lý lỗi tập trung (@RestControllerAdvice)
├── config       Bean cấu hình, dữ liệu mẫu
└── scheduler    Tác vụ chạy định kỳ
src/main/resources
├── templates    Giao diện Thymeleaf
└── application.yml, application-dev.yml, application-prod.yml
```

## Các module đã làm

| Module | Nội dung |
|---|---|
| 1 | Tạo project, API `/hello` |
| 2 | Bean & IoC: `UtilityService`, bean `PasswordEncoder` |
| 3 | REST API cơ bản (dữ liệu trong bộ nhớ) |
| 4 | Spring Data JPA + MySQL, quan hệ Employee – Department, CRUD, tìm kiếm |
| 5 | Validation, xử lý lỗi tập trung |
| 6 | Giao diện Thymeleaf: danh sách, thêm, tìm kiếm |
| 7 | Logging, profile dev / prod |
| 8 | Actuator, caching, scheduling |
| 9 | Spring Security: đăng ký, đăng nhập, JWT, phân quyền |
| 10 | Thống kê bằng `@Query`, trang `/employees/statistics` |

## Kết quả chạy

### Đăng nhập, đăng ký

| Đăng nhập | Đăng ký: báo lỗi dữ liệu |
|---|---|
| ![](docs/images/01-login.png) | ![](docs/images/02-register-validation.png) |

### Quản lý nhân viên (ADMIN)

**Danh sách nhân viên**

![](docs/images/03-admin-employee-list.png)

| Thêm nhân viên: báo lỗi từng ô | Thêm thành công |
|---|---|
| ![](docs/images/04-add-employee-validation.png) | ![](docs/images/05-add-employee-success.png) |

**Tìm kiếm theo tên + phòng ban**

![](docs/images/06-search.png)

### Thống kê

![](docs/images/07-statistics.png)

### Phân quyền (USER)

| USER không thấy nút Thêm | USER vào trang thêm → 403 |
|---|---|
| ![](docs/images/08-user-employee-list.png) | ![](docs/images/09-user-forbidden-page.png) |

### REST API

| Đăng nhập, nhận JWT | Lấy danh sách nhân viên |
|---|---|
| ![](docs/images/10-api-login.png) | ![](docs/images/11-api-employees.png) |

| API thống kê | USER thêm nhân viên → 403 |
|---|---|
| ![](docs/images/12-api-statistics.png) | ![](docs/images/13-api-user-forbidden.png) |

| Dữ liệu sai → 400 | Chưa đăng nhập → 401 |
|---|---|
| ![](docs/images/14-api-validation-error.png) | ![](docs/images/15-api-unauthorized.png) |

| Báo cáo có cache | Actuator health |
|---|---|
| ![](docs/images/16-api-report-cache.png) | ![](docs/images/17-actuator-health.png) |
