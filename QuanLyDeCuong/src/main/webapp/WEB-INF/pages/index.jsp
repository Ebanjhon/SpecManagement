<%-- 
    Document   : index
    Created on : 28 Apr 2024, 1:38:17 am
    Author     : eban
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!-- Latest compiled and minified CSS -->

<!DOCTYPE html>

<h1>Welcom to my fucking website!🖕<h1>
        <form action="api/payment" method="post">
            <label for="amount">Số tiền:</label>
            <input type="text" id="amount" name="amount" required>
            <button type="submit">Nạp tiền</button>
        </form>

