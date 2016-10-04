<%-- 
    Document   : edituser
    Created on : Sep 8, 2016, 1:21:46 PM
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
        <title>Edit User</title>
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
            <p id = "logout"><a href="<%=request.getContextPath()%>/izlaz">logout</a></p>
            <%}%>


            <style>
                #logout{
                    float: right;
                    color: black;
                }
            </style>
            <div align="center"> <form:form name="editForm" method="POST" action="edituser" commandName="editUser" >
                    <table>
                        <form:hidden path="user_id" />
                        <form:hidden path="password" />
                        <tr>
                            <td>Name:</td>
                            <td><form:input path="name" /></td>
                            <td style="font-size: small; color: red; font-style: italic;"><form:errors path="name" /></td>
                        </tr>
                        <tr>
                            <td>E-mail:</td>
                            <td><form:input path="email"  /></td>
                            <td style="font-size: small; color: red; font-style: italic;"><form:errors path="email" /></td>
                        </tr>
                        <tr>
                            <td>Username:</td>
                            <td><form:input path="username" readonly="true"  /></td>
                            <td style="font-size: small; color: red; font-style: italic;"><form:errors path="username" /></td>
                        </tr>     
                        <tr>
                            <td>Rola</td>
                            <td><form:input path="rola"/></td>
                            <td style="font-size: small; color: red; font-style: italic;"><form:errors path="rola" /></td>
                        </tr>   
                        <tr>
                            <td>Slika</td>
                            <td><form:input path="slika" style="width: 144px;height: 20px;" /></td>
                            <td style="font-size: small; color: red; font-style: italic;"><form:errors path="slika" /></td>
                        </tr> 
                        <tr>
                            <td></td>
                            <td>
                                <input type="submit" value="Edit" style="width: 100%;" />
                            </td>
                        </tr>
                    </table>
                </form:form>
            </div>
            <%@include file="Html/footer.html"%>

        </div>
    </body>
</html>
