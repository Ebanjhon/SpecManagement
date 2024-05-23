<%-- 
    Document   : header
    Created on : 29 Apr 2024, 3:09:52 pm
    Author     : eban
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<header class="p-3 text-bg-dark" style="position: fixed; width: 100%">
    <div class="container">
        <div class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">
            <a href="/" class="d-flex align-items-center mb-2 mb-lg-0 text-white text-decoration-none">
                <svg class="bi me-2" width="40" height="32" role="img" aria-label="Bootstrap"><use xlink:href="#bootstrap"></use></svg>
            </a>

            <ul class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">
                <li><a href="<c:url value="/" />" class="nav-link" style="font-weight: 700; color: white">HOME</a></li>
                <li style="width: 230px">
                    <button class="btn btn-dark dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false" style="width: 100%">
                        Các dịch vụ
                    </button>
                    <ul class="dropdown-menu dropdown-menu-dark" style="width: 14%">
                        <li><a class="dropdown-item" href="<c:url value="/specifications" />">Các đề cương</a></li>
                        <li><a class="dropdown-item" href="<c:url value="/subjects" />">Môn học</a></li>
                        <li><a class="dropdown-item" href="<c:url value="/courses" />">Khóa học</a></li>
                        <li><a class="dropdown-item" href="<c:url value="/grandies" />">Cột điểm</a></li>
                        <li><a class="dropdown-item" href="<c:url value="/accounts" />">Quản lý tài khoản</a></li>
                    </ul>
                </li>
                <li><a href="#" class="nav-link px-2 text-white">Thong ke</a></li>
                <li><a href="#" class="nav-link px-2 text-white">About US</a></li>
            </ul>

            <div class="text-end">
                <button type="button" class="btn btn-outline-light me-2">Login</button>
                <button type="button" class="btn btn-warning">Sign-up</button>
            </div>
        </div>
    </div>
</header>