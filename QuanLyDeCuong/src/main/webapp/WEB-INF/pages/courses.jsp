<%-- 
    Document   : courses
    Created on : 23 May 2024, 10:37:56 am
    Author     : eban
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>


<div style="display: flex;flex-direction: column ; align-items: center; width: 100%">
    <h3>List Courses</h3>
    <!--hien thi form them khoa hoc-->

    <c:url value="/courses" var="action"/>
    <form:form method="post" action="${action}" modelAttribute="course" style="width: 100%" accept-charset="UTF-8">
        <%--<form:errors path="nameSubject" element="div" cssClass="alert alert-danger"/>--%>

        <div class="mb-3" >
            <form:hidden path="idCourse"/>
            <label class="form-label">Tên Khóa Học</label>
            <form:input type="text" class="form-control" path="nameCourse" id="name" placeholder="Name course..."/>
            <%--<form:errors path="nameCourse" element="div" cssClass="text-danger"/>--%>
        </div>

        <!--chon de cuong-->
        <label class="form-label">Chọn Đề Cương</label>
        <div class="mb-3" >
            <form:select class="form-select" id="specID" path="specID.idSpec">
                <c:forEach items="${specs}" var="s">
                    <c:choose>
                        <c:when test="${s.idSpec == course.specID.idSpec}">
                            <option value="${s.idSpec}" selected>${s.nameSpec}</option>
                        </c:when>
                        <c:otherwise>
                            <option value="${s.idSpec}">${s.nameSpec}</option>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </form:select>
        </div>
        <!--chon giang vien-->
        <label class="form-label">Chọn Giảng Viên</label>
        <div class="mb-3" >
            <form:select class="form-select" id="teacherID" path="teacherID.idUser">
            <c:forEach items="${teachers}" var="t">
                <c:choose>
                    <c:when test="${t.idUser == course.teacherID.idUser}">
                        <option value="${t.idUser}" selected>${t.firstname} ${t.lastname}</option>
                    </c:when>
                    <c:otherwise>
                        <option value="${t.idUser}">${t.firstname} ${t.lastname}</option>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </form:select>
        </div>
        

        <!--thực hiện hành động-->
        <div style="display: flex; justify-content: flex-end;">
            <button type="submit" class="btn btn-primary mt-2">
                <c:choose>
                    <c:when test="${course.idCourse > 0}">Updating</c:when>
                    <c:otherwise>Create course</c:otherwise>
                </c:choose>
            </button>
        </div>
    </form:form>
    <hr class="hr">
    <!--hien thi danh sach-->
    <h3>List Courses</h3>
    <table class="table table-striped">
        <thead>
            <tr style="text-align: center">
                <th scope="col">ID</th>
                <th scope="col">name Course</th>
                <th scope="col">name Teacher</th>
                <th scope="col">name Specification</th>
                <th scope="col">edit</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${courses}" var="c">
                <tr style="text-align: center">
                    <td>${c.idCourse}</td>
                    <td>${c.nameCourse}</td>
                    <td>${c.teacherID.firstname} ${c.teacherID.lastname}</td>
                    <td>${c.specID.nameSpec}</td>
                    <td>
                        <form action="/courses/${c.idCourse}" method="post">
                            <input type="hidden" name="_method" value="delete">
                            <a class="btn btn-warning" href="<c:url value="/courses/${c.idCourse}"/>">Updating</a>
                            <a class="btn btn-danger" href="<c:url value="/deleteCourse/${c.idCourse}"/>" type="submit" onclick="return confirm('ban co chac chan mua xoa?')">Delete</a>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>