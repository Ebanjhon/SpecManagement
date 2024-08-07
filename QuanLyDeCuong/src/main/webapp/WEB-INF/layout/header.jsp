<%-- 
    Document   : header
    Created on : 29 Apr 2024, 3:09:52 pm
    Author     : eban
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<header class="p-3 text-bg-dark" style="position: fixed; width: 100%">
    <div class="container">
        <div class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start" style="height: 40px">
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
                        <!--<li><a class="dropdown-item" href="<c:url value="/grandies" />">Cột điểm</a></li>-->
                        <li><a class="dropdown-item" href="<c:url value="/accounts" />">Quản lý tài khoản</a></li>
                    </ul>
                </li>
                <li><a href="<c:url value="/stats" />" class="nav-link px-2 text-white">Thống kê</a></li>
                <li><a href="#" class="nav-link px-2 text-white">About US</a></li>
            </ul>

            <div class="text-end" style="width: 300px; display: flex; justify-content: space-between; text-align: center; height: 40px;">
                <c:choose>
                    <c:when test="${pageContext.request.userPrincipal == null}">
                        <a class="btn btn-info" href="<c:url value="/login" />">Đăng nhập</a>
                    </c:when>
                    <c:when test="${pageContext.request.userPrincipal != null}">
                        <h4 style="line-height: 40px">Hello ${pageContext.request.userPrincipal.name}!</h4>
                        <a class="btn btn-outline-info" href="<c:url value="/logout" />">Đăng xuất</a>
                    </c:when>
                </c:choose>
            </div>
        </div>
    </div>
</header>
