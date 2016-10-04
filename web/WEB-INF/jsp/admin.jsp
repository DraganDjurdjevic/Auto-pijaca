<%-- 
    Document   : admin
    Created on : Sep 7, 2016, 2:25:03 PM
    Author     : Dragan
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href='https://fonts.googleapis.com/css?family=Open+Sans:400,400italic,700,700italic&subset=latin,latin-ext' rel='stylesheet' type='text/css'>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
        <title>Admin Page</title>
    </head>
    <body>
        <div id="wrapper">
            <c:url var="slika1" value="/resources/images/polovniautomobili1.gif" />
            <c:url var="slika2" value="/resources/images/slika1mini.jpg" />
            <c:url var="slika3" value="/resources/images/slika2mini.jpg" />
            <%@include file="Html/header.html"%>
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
                #logout{
                    float: right;
                    color: black;
                }
            </style>
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
                    align-items:  center;
                    width: 300px;
                    border: 1px solid;
                    float: right;

                }
                #edit{
                    border-radius: 5px;
                    background-color:  #50a2f5;
                    height: 30px; 
                    width: 70px;
                    font-size: 14px;
                }
                #delete{
                    border-radius: 5px;
                    background-color:  #b22127;
                    height: 30px; 
                    width: 70px;
                    font-size: 14px;
                }
                #add{
                    border-radius: 5px;
                    background-color:  greenyellow;
                    height: 50px; 
                    width: 300px;
                    font-size: 14px; 
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

            </style>
            <h3> Dobrodosli na admin stranu : <span style = "color: #f1a41a;">${user.name}</span><span id = "loguot"><a href="<%=request.getContextPath()%>/izlaz">logout</a></span></h3>
            <h1>Lista svih usera : <span style="float: right;"><a href="<%=request.getContextPath()%>/registration"><button id = "add">Add new User</button></a></span></h1>
            <div id = "levi"><table style="border: 1px solid;">
                    <tr>
                        <td>Name</td>
                        <td>Username</td>
                        <td>Email</td>
                    </tr>
                    <c:forEach items="${users}" var="user">
                        <tr>
                            <td>${user["name"]}</td>
                            <td>${user["username"]}</td>
                            <td>${user["email"]}</td>
                            <td ><a href="<%=request.getContextPath()%>/edituser?id=${user.user_id}"><button id = "edit">Edit</button></a></td>
                            <td ><a href="<%=request.getContextPath()%>/deleteUser?id=${user.user_id}"><button id = "delete">Delete</button></a></td>
                        </tr>
                    </c:forEach>
                </table>
                <h1>Lista oglasa koji cekaju odobrenje: </h1>
                <table style="border: 1px solid;">
                    <tr>
                        <td>Brend</td>
                        <td>Model</td>
                        <td>Godiste</td>
                        <td>Cena</td>
                        <td>Opis oglasa</td>
                        <td>Korisnik</td>
                    </tr>
                    <c:forEach items="${status}" var="status">
                        <tr>
                            <td>${status["brend"]}</td>
                            <td>${status["model"]}</td>
                            <td>${status["godiste"]}</td>
                            <td>${status["cena"]}</td>
                            <td>${status["tekst"]}</td>
                            <td>${status["korisnik"]}</td> 
                            <td ><a href="<%=request.getContextPath()%>/odobriOglas?id=${status.oglas_id}"><button id = "edit">Odobri</button></a></td>
                            <td ><a href="<%=request.getContextPath()%>/deleteOglas?id=${status.oglas_id}"><button id = "delete">Odbaci</button></a></td>
                        </tr>
                    </c:forEach>
                </table>
            </div>

            <div id = "desni">

                <h5 style="background-color: #EEEEEE;">Vasi podaci su :</h5>
                <div id = "profil_slika">
                    <c:url var="slika1" value="${user.slika}"/>
                    <img src="${slika1}">
                    <form id = "upload" action="uploadfile" method="post" enctype="multipart/form-data">
                        <input style = "width: 80px;" type="file" name="file1">
                        <input type="submit" value="Upload">
                    </form>
                </div>
                <strong> Name : ${user.name}</strong><br>
                <strong> UserName :${user.username}</strong><br>
                <strong> Email :${user.email}</strong><br><br>
            </div>


            <%@include file="Html/footer.html"%>
        </div>

    </body>
</html>
