<%-- 
    Document   : accounts
    Created on : 23 May 2024, 11:03:38 am
    Author     : eban
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>

<html>
    <head>
        <title>Tạo tài khoản</title>
        <style>
            .container-account {
                display: flex;
                flex-direction: column;
                align-items: center;
                width: 70%;
            }
            .form-container {
                margin-bottom: 10px;
                background: #fff;
                padding: 20px;
                border-radius: 8px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
                width: 400px;
                display: flex;
                flex-direction: column;
            }
            .form-container input, .form-container select {
                padding: 10px;
                margin-bottom: 15px;
                border: 1px solid #ddd;
                border-radius: 4px;
                width: 100%;
                box-sizing: border-box;
            }
            .form-container input[type="submit"] {
                background: #5cb85c;
                color: #fff;
                border: none;
                cursor: pointer;
                font-size: 16px;
            }
            .alert {
                display: none;
                padding: 20px;
                margin-top: 10px;
                border-radius: 4px;
                color: #3c763d;
                background-color: #dff0d8;
                border-color: #d6e9c6;
            }
        </style>
    </head>
    <body>
        <div class="container-account">
            <h1>TẠO TÀI KHOẢN THEO YÊU CẦU NGƯỜI DÙNG</h1>
            <form id="createUserForm" class="form-container" enctype="multipart/form-data">
                <label for="firstName">Họ:</label>
                <input type="text" id="firstName" name="firstName" required>

                <label for="lastName">Tên:</label>
                <input type="text" id="lastName" name="lastName" required>

                <label for="username">Tên Đăng Nhập:</label>
                <input type="text" id="username" name="username" required>


                <label for="email">Email:</label>
                <input type="email" id="email" name="email" required>

                <label for="role">Vai Trò:</label>
                <select id="role" name="role" required>
                    <option value="ROLE_TEACHER">GIẢNG VIÊN</option>
                    <option value="ROLE_STUDENT">SINH VIÊN</option>
                </select>

                <label for="gender">Giới Tính:</label>
                <select id="gender" name="gender" required>
                    <option value="Nam">NAM</option>
                    <option value="Nữ">NỮ</option>
                </select>

                <input type="submit" value="Tạo Tài Khoản">
            </form>

            <div id="successMessage" class="alert">
                <c:if test="${not empty successMessage}">
                    ${successMessage}
                </c:if>
            </div>
        </div>

        <!--danh sach-->
        <table class="table table-striped">
            <thead>
                <tr style="text-align: center">
                    <th scope="col">ID</th>
                    <th scope="col">UserNamm</th>
                    <th scope="col">FristName</th>
                    <th scope="col">LastName</th>
                    <th scope="col">DateOfBirth</th>
                    <th scope="col">Gender</th>
                    <th scope="col">Email</th>
                    <th scope="col">Adress</th>
                    <th scope="col">Phone</th>
                    <th scope="col">Role</th>
                    <th scope="col">Trạng thái</th>
                    <th scope="col">Coin</th>
                    <th scope="col">Xóa</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${specs}" var="s">
                    <tr style="text-align: center">
                        <td>${s.idSpec}</td>
                        <td>${s.nameSpec}</td>
                        <td>${s.authorID.firstname} ${s.authorID.lastname}</td>
                        <td>${s.credit}</td>
                        <td><button class="btn btn-outline-success">Xem</button></td>
                        <td>${s.subjectID.nameSubject}</td>
                        <td><fmt:formatDate value="${s.dateCreate}" pattern="yyyy-MM-dd" /></td>
                        <td>${s.typeSpecID.nameType}</td>
                        <td><button class="btn btn-outline-success">Tai ve</button></td>
                        <td>${s.hoiDongID.idHoiDong}</td>
                        <td>${s.status}</td>
                        <td>
                <form action="" method="post">
                    <input type="hidden" name="_method" value="delete">
                    <a class="btn btn-danger" href="<c:url value="/deleteSubject/"/>" type="submit" onclick="return confirm('Are you sure you want to delete this subject?')">Delete</a>
                </form>
            </td>
        </tr>
    </c:forEach>
</tbody>
</table>




<script>
    document.getElementById('createUserForm').addEventListener('submit', function (e) {
        e.preventDefault();
        var formData = new FormData(this);

        fetch('/QuanLyDeCuong/api/users/createUserBy/', {
            method: 'POST',
            body: formData
        })
                .then(response => response.text())
                .then(responseText => {
                    document.getElementById('successMessage').style.display = 'block';
                    document.getElementById('successMessage').innerText = responseText;
                })
                .catch(error => {
                    alert('Có lỗi xảy ra: ' + error);
                });
    });
</script>
</body>
</html>