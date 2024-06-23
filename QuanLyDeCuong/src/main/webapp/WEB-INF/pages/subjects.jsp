<%-- 
    Document   : subjects
    Created on : 29 Apr 2024, 4:29:48 pm
    Author     : eban
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>

<div style="display: flex;flex-direction: column ; align-items: center; width: 100%">
    <h3>Thêm môn học</h3>

    <c:url value="/subjects" var="action" />
    <form:form method="post" action="${action}" modelAttribute="sub" style="width: 100%" accept-charset="UTF-8">
        <form:errors path="nameSubject" element="div" cssClass="alert alert-danger"/>

        <div class="mb-3" >
            <form:hidden path="idSubject"/>
            <label class="form-label">Tên môn học</label>
            <form:input type="text" class="form-control" path="nameSubject" id="name" placeholder="Name subject..."/>
            <form:errors path="nameSubject" element="div" cssClass="text-danger"/>

        </div>
        <div style="display: flex; justify-content: flex-end;">
            <button type="submit" class="btn btn-primary">
                <c:choose>
                    <c:when test="${sub.idSubject > 0}"> Cập nhât môn học</c:when>
                    <c:otherwise> Thêm môn học</c:otherwise>
                </c:choose>
            </button>
        </div>
    </form:form>
   <hr class="hr" />
    <!--danh sach-->
    <h3>Danh sách môn học</h3>
    <table class="table table-striped" style="width: 100%">
        <thead>
            <tr style="text-align: center">
                <th scope="col">ID</th>
                <th scope="col">Tên Môn Học</th>
                <th scope="col">Cập nhật/xóa</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${subs}" var="s">
                <tr style="text-align: center">
                    <td>${s.idSubject}</td>
                    <td>${s.nameSubject}</td>
                    <td>
                        <form action="/subjects/${s.idSubject}" method="post">
                            <input type="hidden" name="_method" value="delete">
                            <a class="btn btn-warning" href="<c:url value="/subjects/${s.idSubject}"/>">Updating</a>
                            <a class="btn btn-danger" href="<c:url value="/deleteSubject/${s.idSubject}"/>" type="submit" onclick="return confirm('Are you sure you want to delete this subject?')">Delete</a>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>