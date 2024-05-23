<%-- 
    Document   : courses
    Created on : 23 May 2024, 10:37:56 am
    Author     : eban
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>

<c:url value="/courses" var="action"/>
<div style="display: flex;flex-direction: column ; align-items: center; width: 100%">
    <h3>List Courses</h3>
    <!--hien thi form them khoa hoc-->
    <form:form method="post" action="${action}" modelAttribute="course" style="width: 100%" accept-charset="UTF-8">
        <%--<form:errors path="nameSubject" element="div" cssClass="alert alert-danger"/>--%>

        <div class="mb-3" >
            <form:hidden path="idCourse"/>
            <label class="form-label">Name Course</label>
            <form:input type="text" class="form-control" path="nameCourse" id="name" placeholder="Name course..."/>
            <form:errors path="nameCourse" element="div" cssClass="text-danger"/>

        </div>
            
            
        <div style="display: flex; justify-content: flex-end;">
            <button type="submit" class="btn btn-primary">
                <c:choose>
                    <c:when test="${course.idCourse > 0}"> Cập nhât</c:when>
                    <c:otherwise>Add course</c:otherwise>
                </c:choose>
            </button>
        </div>
    </form:form>


    <!--hien thi danh sach-->
    <table class="table table-striped">
        <thead>
            <tr>
                <th scope="col">ID</th>
                <th scope="col">name Course</th>
                <th scope="col">name Teacher</th>
                <th scope="col">name Specification</th>
                <th scope="col">edit</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${courses}" var="c">
                <tr>
                    <td>${c.idCourse}</td>
                    <td>${c.nameCourse}</td>
                    <td>${c.teacherID.firstname} ${c.teacherID.lastname}</td>
                    <td>${c.specID.nameSpec}</td>
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