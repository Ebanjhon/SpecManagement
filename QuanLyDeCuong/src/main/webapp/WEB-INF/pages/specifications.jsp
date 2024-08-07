<%-- 
    Document   : specifications
    Created on : 23 May 2024, 10:52:58 am
    Author     : eban
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>

<div style="display: flex;flex-direction: column ; align-items: center; width: 100%">
    <h3>List Specification</h3>
    <!--form tim kiem-->
    <form class="container" action="<c:url value="/specifications"/>">
        <div class="col-md-6 mx-auto">
            <input class="form-control border rounded-pill" type="search" name="kw" placeholder="Search...">
        </div>
    </form>

    <hr><!-- comment -->

    <!--danh sach-->
    <table class="table table-striped">
        <thead>
            <tr style="text-align: center">
                <th scope="col">ID</th>
                <th scope="col">Tên đề cương</th>
                <th scope="col">Tác giả</th>
                <th scope="col">Số tín chỉ</th>
                <th scope="col">Nội dung</th>
                <th scope="col">Môn học</th>
                <th scope="col">Ngày tạo</th>
                <th scope="col">Loại đề cương</th>
                <th scope="col">File</th>
                <th scope="col">Hội đồng</th>
                <th scope="col">Trạng thái</th>
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
</div>

<!--<div class="container shadow min-vh-100 py-4">-->
