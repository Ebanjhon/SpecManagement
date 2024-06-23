<%-- 
    Document   : base
    Created on : 29 Apr 2024, 3:10:14 pm
    Author     : eban
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>
            <tiles:insertAttribute name="title"/>
        </title>
        <link rel="icon" type="image/x-icon" href="https://res.cloudinary.com/drosiimsz/image/upload/v1719122047/images_yq0x35.png">
        <link rel="stylesheet" href="styles.css">
        <script src="https://kit.fontawesome.com/dd307a4d09.js" crossorigin="anonymous"></script>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    </head>
    <body>
        <tiles:insertAttribute name="header"/>

        <div class="container d-flex justify-content-center" style="min-height: 573px; width: 100%; padding-top: 120px">
            <tiles:insertAttribute name="content"/>
        </div>

        <tiles:insertAttribute name="footer"/>
    </body>
</html>


