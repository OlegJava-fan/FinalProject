<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>My orders</title>
    <my:bootstrapLink/>
    <link rel="stylesheet" href="style/main.css">
    <script src="sort.js"></script>
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
            <div class="col-sm-6">
                <div class="row" id="logoText">
                    <img src="img/SU.png" alt="text.png">
                </div>
            </div>
            <div class="col-sm-2">
                <my:logout/>
            </div>
        </div>
    </div>
    <nav id="navBar" class="navbar navbar-expand-sm ">
        <my:myAllFacultiesButton/>
        <my:myResultButton/>
        <div class="pl-sm-2 pt-sm-2">
            <form action="controller" method="get">
                <div class="button">
                    <input type="hidden" name="certificate_id" value="${sessionScope.account.certificate_id}"/>
                    <input type="hidden" name="firstName" value="${sessionScope.account.firstName}"/>
                    <input type="hidden" name="middleName" value="${sessionScope.account.middleName}"/>
                    <input type="hidden" name="lastName" value="${sessionScope.account.lastName}"/>
                    <input type="hidden" name="city" value="${sessionScope.account.city}"/>
                    <input type="hidden" name="region" value="${sessionScope.account.region}"/>
                    <input type="hidden" name="eMail" value="${sessionScope.account.EMail}"/>
                    <input type="hidden" name="command" value="findMyCertificate">
                    <button class="btn btn-sm" type="submit">My info</button>
                </div>
            </form>
        </div>
    </nav>
</header>
<body>
<div>
    <table border="1" class="table table-hover table_sort text-center text-white my-table-class">
        <thead>
        <tr>
            <th>Faculties name</th>
            <th>Free form places</th>
            <th>Pay form places</th>
            <th>all places</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${sessionScope.facultiesListGroupByOrder}" var="facultiesList" varStatus="counter">
        <tr>
            <td class="font-weight-bold">${facultiesList.name} </td>
            <td>${facultiesList.freeFormPlaces}</td>
            <td>${facultiesList.payFormPlaces}</td>
            <td>${facultiesList.allPlace}</td>
        </tr>
        </c:forEach>
        <tbody>
    </table>
</div>
<my:bootstrapScript/>
</body>
</html>
