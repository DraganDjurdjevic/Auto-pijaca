<%-- 
    Document   : galery
    Created on : Aug 25, 2016, 1:14:25 PM
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
        <title>Galery</title>
    </head>
    <body>
        <div id="wrapper">
            <c:url var="slika1" value="/resources/images/polovniautomobili1.gif" />
            <c:url var="slika2" value="/resources/images/slika1mini.jpg" />
            <c:url var="slika3" value="/resources/images/slika2mini.jpg" />
            <c:url var="slika4" value="/resources/images/slika3mini.jpg" />
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
            <p id = "logout"><a href="<%=request.getContextPath()%>/izlaz">logout</a></p>
            <%}%>



            <style>
                #logout{
                    float: right;
                    color: black;
                }
            </style>
            <div align = "center"><h2>Ovde mozete pogledati najnovije modele. </h2><div>
            <table style = "margin-left: 50px;">
                <tr>
                    <td><img src="${slika1}" alt="Logo Lineweb"></td>
                    <td><img src="${slika2}" alt="Logo Lineweb"></td>
                    <td><img src="${slika3}" alt="Logo Lineweb"></td>
                    <td><img src="${slika4}" alt="Logo Lineweb"></td>
                </tr>
                <tr>
                    <td></td>
                    <td colspan="2"> <a href="<%=request.getContextPath() %>/galeryslaider"><button style="background-color:  red; width: 400px; height: 50px;border-radius: 10px;">Galerija</button></a></td>
                </tr>
           
            </table>
            <%@include file="Html/footer.html"%>
        </div>
    </body>
</html>
