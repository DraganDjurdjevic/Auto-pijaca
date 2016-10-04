<%-- 
    Document   : postavioglas
    Created on : Sep 6, 2016, 10:25:10 AM
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
        <title>Pstavi oglas</title>
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
            <%}%>
            <%
                if (session.getAttribute("username") != null) {
            %>
            <p id = "logout"><a href="<%=request.getContextPath()%>/izlaz">logout</a></p>
            <%}%>
            <style>
                #logout{
                    float: right;
                    color: black;
                }
            </style>

            <div align = "center">
                <h2>Forma za postavljanje oglasa : </h2>
                <form:form method="POST" commandName="PostaviOglas">
                    <table>
                        <tr>
                            <td>Izaberi automobil : </td>
                            <td><form:select path="brend">
                                    <form:option value="" label="...." />
                                    <form:options items="${brendovi}" />
                                </form:select>
                            </td>
                        </tr>
                        <tr>

                            <td><form:errors path="brend"/></td>
                        </tr>
                        <tr>
                            <td>Izaberi model : </td>
                            <td><form:select path="model">
                                    <form:option value="" label="...." />
                                    <form:options items="${modeli}" />
                                </form:select>
                            </td>
                        </tr>
                        <tr>

                            <td><form:errors path="model"/></td>
                        </tr>
                        
                         <tr>
                            <td>Izaberi godiste : </td>
                            <td><form:select path="godiste">
                                    <form:option value="" label="...." />
                                    <form:options items="${godiste}" />
                                </form:select>
                            </td>
                        </tr>
                        <tr>

                            <td><form:errors path="godiste"/></td>
                        </tr>

                        <tr>
                            <td>Opis oglasa :</td>
                            <td><form:input path="tekst"/></td>
                        </tr>
                        <tr>
                            <td>Cena : </td>
                            <td><form:input path="cena"/></td>
                        </tr>
                        <tr>
                            <td><input type="submit" name="submit" value="Sacuvaj"></td>
                        </tr>
                        <tr>
                    </table>
                </form:form>

            </div>


            <%@include file="Html/footer.html"%>

        </div>
    </body>
</html>
