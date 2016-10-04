<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">


<html>
    <head>
        <link href='https://fonts.googleapis.com/css?family=Open+Sans:400,400italic,700,700italic&subset=latin,latin-ext' rel='stylesheet' type='text/css'>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">
        <script src="<c:url value="/resources/js/jquery-3.1.0.min.js" />"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
        <title>Home</title>
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
                .pretrazivac td{
                    padding-left: 50px;
                }
                #pretraga{
                    width: 100px;
                    height: 30px;
                    border-radius: 20px;
                    background-color: darkorange;
                }

                .sviOglasi td{
                    padding-left: 30px;
                    padding-bottom: 20px;

                }
            </style>

            <script type="text/javascript">
                function onPromenaBrenda() {
                    $.ajax({
                        type: "get",
                        url: "<%=request.getContextPath()%>/onPromenaBrenda",
                        cache: false,
                        contentType: "application/json",
                        data: 'brend=' + $("#brend").val(),
                        success: function (response) {
                            var obj = response;
                            var len = response.length;
                            var html = '';
                            for (var i = 0; i < len; i++) {
                                html += '<option value="' + obj[i].model_id + '">'
                                        + obj[i].naziv + '</option>';
                            }
                            $("#model").html(html);
                        },
                        error: function () {
                            alert('Error while request..');
                        }
                    });
                }
            </script>

            <form:form method="POST" commandName="Oglas">
                <table class = "pretrazivac">
                    <tr>
                        <td>Izaberi automobil : </td>
                        <td>Izaberi model : </td>
                        <td>Cena do : </td>
                    </tr>
                    <tr>
                        <td><form:select path="brend" onchange="onPromenaBrenda();">
                                <form:option value="" label="..izaberi automobil.." />
                                <c:forEach items="${brendovi}" var="brend">
                                    <form:option value="${brend.brend_id}" label="${brend.naziv}" />
                                </c:forEach>
                            </form:select>
                        </td>

                        <td><form:select path="model">
                                <form:option value="" label="..izaberi model.." />
                            </form:select>
                        </td>
                        <td><form:input path="cena"/></td>

                        <td><input id ="pretraga" type="submit" name="submit" value="Pretrazi"></td>
                    </tr>

                </table>
            </form:form>

            <h1>Lista oglasa: </h1> 
            <table class = "sviOglasi">

                <c:forEach items="${oglasi}" var="oglas">
                    <tr style=" border-bottom:1px solid black;">

                        <td><span style="font-size: 30px;">${oglas["brend"]}</td>
                        <td>
                            <span style="color:darkorange;">Model : </span>${oglas["model"]}<br>
                            <span style="color:darkorange;">Cena : </span>${oglas["cena"]}<br>
                            <span style="color:darkorange;">Model : </span>${oglas["godiste"]}<br>

                        </td>


                        <td><span style="color:darkorange;">Opis oglasa : </span>${oglas["tekst"]}</td>

                    </tr>

                </c:forEach>

            </table>

            <%@include file="Html/footer.html"%>

        </div>

    </body>
</html>
