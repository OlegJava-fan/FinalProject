<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<html>
<head>
    <meta charset="UTF-8">
    <title>FinalProject</title>
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
        <div class="mt-sm-3 col-sm-12 d-flex justify-content-sm-end" id="loginPageRegButton">
            <form action="registration.jsp">
                <div class="button">
                    <button type="submit" class="btn btn-sm">Registration</button>
                </div>
            </form>
        </div>
    </nav>
</header>
<body>
<div class="container-fluid">
    <div class="d-flex justify-content-sm-center mt-sm-3">
        <c:if test="${not empty sessionScope.errorMassage}">
            <h1>${sessionScope.errorMassage}</h1></c:if>
        <c:remove var="errorMassage" scope="session"/>
    </div>
    <div class="d-flex justify-content-sm-center mt-sm-3" id="regSuccessfully">
        <c:if test="${not empty sessionScope.regSuccessfully}">
            <h1>${sessionScope.regSuccessfully}</h1></c:if>
        <c:remove var="regSuccessfully" scope="session"/>
    </div>
    <div class="d-flex justify-content-center mt-sm-3">
        <form class="container-fluid" action="controller" method="post">
            <div class="row align-items-center mt-sm-2 ">
                <div class="col-sm-6 d-flex justify-content-sm-end  ">
                    <label class="mb-sm-0" for="login">
                        <h1 class=" mb-sm-0">Login:</h1>
                    </label>
                </div>
                <div class="col-sm-6">
                    <input class="mb-sm-0" type="text" id="login" name="login">
                </div>
            </div>
            <div class="row align-items-center mt-sm-2 ">
                <div class="col-sm-6 d-flex justify-content-sm-end ">
                    <label class=" mb-sm-0" for="password">
                        <h1 class=" mb-sm-0">Password:</h1>
                    </label>
                </div>
                <div class="col-sm-6">

                    <input class="mb-sm-0" type="password" id="password" name="password">

                </div>
            </div>
            <div class="row mt-sm-2 ">
                <div class="col-sm-6 d-flex justify-content-sm-end ">
                    <div class="button">
                        <input type="hidden" name="command" value="login"/>
                        <button type="submit" class="btn btn-sm">Submit</button>
                    </div>
                </div>
            </div>
        </form>
    </div>
</div>
<my:bootstrapScript/>
</body>
</html>
