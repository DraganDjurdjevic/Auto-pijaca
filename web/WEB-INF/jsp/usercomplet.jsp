<%-- 
    Document   : user
    Created on : Aug 29, 2016, 2:37:54 PM
    Author     : Dragan
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <link href='https://fonts.googleapis.com/css?family=Open+Sans:400,400italic,700,700italic&subset=latin,latin-ext' rel='stylesheet' type='text/css'>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
        <title>User Page</title>
    </head>
    <body>
        <%
                if (session.getAttribute("username") == null) {
            %>
            <style>
                #profil{
                    display: none;
                }
            </style>
            <%}%>


        <div id="wrapper">
            <c:url var="slika1" value="/resources/images/polovniautomobili1.gif" />
            <c:url var="slika2" value="/resources/images/slika1mini.jpg" />
            <c:url var="slika3" value="/resources/images/slika2mini.jpg" />
            <%@include file="Html/header.html"%>
            
            <div align = "center">
                <h1>Uspesna registracija</h1>
                <h2>Vasi podaci su:</h2>
                <strong> Name : ${um.name}</strong><br>
                <strong> UserName :${um.username}</strong><br>
                <strong> Email :${um.email}</strong><br>
                <strong> Password :${um.password}</strong><br><br>
                <a href="<%=request.getContextPath()%>/login"><button style="background-color: red; height: 50px; width: 150px;">Login</button></a>
            </div>
            <br>
            <%@include file="Html/footer.html"%>
        </div>
    </body>
</html>
