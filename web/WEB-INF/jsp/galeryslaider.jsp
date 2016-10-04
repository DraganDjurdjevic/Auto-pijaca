<%-- 
    Document   : galeryslider
    Created on : Aug 29, 2016, 10:30:10 AM
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
        <title>Galery Slaider</title>
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
                #slike{
                    margin-left: 170px;
                    height: 400px;
                    width: 600px;
                    background-color: red;
                }
                #d{
                    width: 100px;
                    height: 40px;
                    margin-left: 260px;
                    position: absolute;
                }
                #dugme1,#dugme2,#dugme3{
                    border-radius: 25px;
                    background: #fff;
                    width: 15px;
                    height: 15px;
                    float: left;
                    margin-left: 10px;
                }
                #opis{
                    margin-left: 170px;
                    width: 600px;
                    height: 50px;
                    
                }
            </style>
            <c:url var="s1" value="/resources/images/slika1.jpg" />
            <c:url var="s2" value="/resources/images/slika2.jpg" />
            <c:url var="s3" value="/resources/images/slika3.jpg" />
<br />
<br />

            <div id = "slike">  
                <div id="d">
                    <div id="dugme1" onclick="slajder('1');"></div>

                    <div id="dugme2" onclick="slajder('2');"></div>

                    <div id="dugme3" onclick="slajder('3');"></div>
                </div>
            </div>

            <div id = "opis"></div>





            <script>
                var slika = '{"frame1": [{"text":"<p>Prvi automobil</p>", "image":"${s1}"},' +
                        '{"text":"<p>Drugi automobil</p>", "image":"${s2}"},' +
                        '{"text":"<p>Treci automobil</p>", "image":"${s3}"}]}';

                var j = JSON.parse(slika);




                function oznaciDugme(id) {
                    document.getElementById("dugme1").style.background = "#fff";
                    document.getElementById("dugme2").style.background = "#fff";
                    document.getElementById("dugme3").style.background = "#fff";
                    document.getElementById(id).style.background = "#A8252F";
                }

                function slajder(id) {
                    switch (parseInt(id)) {
                        case 1:
                            document.getElementById("slike").style.background = "url(" + j.frame1[0].image + ")";
                            document.getElementById("opis").innerHTML = j.frame1[0].text;
                            oznaciDugme('dugme1');
                            break;
                        case 2:
                            document.getElementById("slike").style.background = "url(" + j.frame1[1].image + ")";
                            document.getElementById("opis").innerHTML = j.frame1[1].text;
                            oznaciDugme('dugme2');
                            break;
                        case 3:
                            document.getElementById("slike").style.background = "url(" + j.frame1[2].image + ")";
                            document.getElementById("opis").innerHTML = j.frame1[2].text;
                            oznaciDugme('dugme3');
                            break;

                    }
                }
                document.getElementById("slike").style.background = "url(" + j.frame1[0].image + ")";
                document.getElementById("opis").innerHTML = j.frame1[0].text;
                oznaciButton1('dugme1');

            </script>




            <%@include file="Html/footer.html"%>
        </div>
    </body>
</html>
