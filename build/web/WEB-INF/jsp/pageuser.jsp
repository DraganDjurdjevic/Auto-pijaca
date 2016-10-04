<%-- 
    Document   : pageuser
    Created on : Sep 2, 2016, 9:25:37 AM
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
        <%}

            if (session.getAttribute("username") != null) {
        %>
        <style>
            #login{
                display: none;
            }

        </style>
        <%}%>

        <style>
            #loguot{
                float: right;
                font-size: 18px;
                color: black ;
            }
            #levi{
                width: 600px;

                float: left; 
            }
            #desni{
                width: 300px;
                border: 1px solid;
                float: right; 
                overflow:hidden;
            }
            #postavi_oglas{
                border-radius: 10px;
                background-color:  red;
                height: 50px; 
                width: 300px;
                font-size: 18px;
                float: right;
            }
            #profil_slika{
                margin-left: 90px;
                border: 1px solid;
                height: 100px;
                width: 100px;
                background-color: white;

            }
            #upload{
                margin-left: 100px;
                margin-top: -55px;
            }
            #delete{
                border-radius: 5px;
                background-color:  #b22127;
                height: 30px; 
                width: 70px;
                font-size: 14px;
            }

        </style>

        <div id="wrapper">
            <c:url var="slika1" value="/resources/images/polovniautomobili1.gif" />
            <c:url var="slika2" value="/resources/images/slika1mini.jpg" />
            <c:url var="slika3" value="/resources/images/slika2mini.jpg" />
            <%@include file="Html/header.html"%>
            <h3> Dobrodosli na vasu stranu : <span style = "color: #f1a41a;">${user.name}</span><span id = "loguot"><a href="<%=request.getContextPath()%>/izlaz">logout</a></span></h3>
            <a href="<%=request.getContextPath()%>/postavioglas"><button id = "postavi_oglas">Postavi novi oglas</button></a>
            <h4>Vasi oglasi :</h4>
            <div id = "levi">
                <table border=1> 
                    <tr>
                        <td>Brend</td>
                        <td>Model</td>
                        <td>Godiste</td>
                        <td>Cena</td>
                        <td>Opis oglasa</td>
                        <td>Status</td>
                    </tr>
                    <c:forEach items="${oglasi}" var="oglas">

                        <tr>
                            <td>${oglas["brend"]}</td>
                            <td>${oglas["model"]}</td>
                            <td>${oglas["godiste"]}</td>
                            <td>${oglas["cena"]}</td>
                            <td>${oglas["tekst"]}</td>
                            <td>${oglas["status"]}</td>
                            <td ><a href="<%=request.getContextPath()%>/deleteOglas?id=${oglas.oglas_id}"><button id = "delete">Delete</button></a></td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
            <div id = "desni">
                <h5 style="background-color: #EEEEEE;">Vasi podaci su :</h5>

                <div id = "profil_slika">
                    <c:url var="slika1" value="${user.slika}"/>
                    <img src="${slika1}" alt="Postavite profilnu sliku">
                    <div id = "upload">
                        <form action="uploadfile" method="post" enctype="multipart/form-data">
                            <input style = "width: 80px;" type="file" name="file">
                            <input type="submit" value="Upload">
                        </form>
                    </div>
                </div>


                <strong> Name : ${user.name}</strong><br>
                <strong> UserName :${user.username}</strong><br>
                <strong> Email :${user.email}</strong><br><br>
            </div>

            <%@include file="Html/footer.html"%>
        </div>
    </body>
</html>
