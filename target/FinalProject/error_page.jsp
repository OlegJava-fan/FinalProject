<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Error Page</title>
    <my:bootstrapLink/>
    <link rel="stylesheet" href="style/main.css">
</head>
<header>
    <div class="container-fluid">
        <div class="row">
            <div class="col-sm-4">
                <div class="d-flex justify-content-sm-end pt-4" id="logo"><img src="img/logo.png" width="150"
                                                                               height="150"
                                                                               alt="logo.png">
                </div>
            </div>
            <div class="col-sm-8">
                <div id="logoText">
                    <img src="img/SU.png" alt="text.png">
                </div>
            </div>
        </div>
    </div>
    <nav id="navBar" class="navbar navbar-expand-sm ">
        <div class="button mt-sm-2">
            <form action="index.jsp">
                <button class="btn btn-sm" type="submit">Login page</button>
            </form>
        </div>
    </nav>
</header>
<body>
<div class="container-fluid mt-sm-3">
    <div class="col-sm-12 d-flex justify-content-sm-center">
        <h3>${sessionScope.errorMassage}</h3></div>
    <c:remove var="errorMassage" scope="session"/>
</div>
<my:bootstrapScript/>
<body>
<html>