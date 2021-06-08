<%@ taglib prefix="sortlib" uri="http://com.my.finalProject/tags" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>FinalTask</title>
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

        <my:myOrdersButton/>
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
<div class="container-fluid mt-sm-2 ">
    <h3 class="col-sm-12 d-flex justify-content-sm-center"> Our faculties: </h3>
</div>
<div class="container-fluid mt-sm-2">
    <table border="1" class="table table-hover text-sm-center text-white">
        <thead>
        <tr>
            <th class="align-middle">
                <div class="row d-flex justify-content-sm-center ">
                    <form class="mb-sm-0" action="controller" method="get">
                        <div class="button">
                            <input type="hidden" name="command" value="sortByNameFaculty">
                            <button class=" btn btn-sm " type="submit"><i class="fas fa-sort-alpha-down"></i></button>
                        </div>
                    </form>

                    <div class="ml-sm-2  mr-sm-2">
                        Faculties name
                    </div>

                    <form class="mb-sm-0" action="controller" method="get">
                        <div class="button">
                            <input type="hidden" name="command" value="sortByNameFacultyReverse">
                            <button class=" btn btn-sm " type="submit"><i class="fas fa-sort-alpha-up"></i></button>
                        </div>
                    </form>

                </div>
            </th>
            <th class="align-middle">
                <div class="row d-flex justify-content-sm-center">
                    <form class="mb-sm-0" action="controller" method="get">
                        <div class="button">
                            <input type="hidden" name="command" value="sortByFreePlaceFaculties">
                            <button class=" btn btn-sm " type="submit"><i class="fas fa-sort-numeric-down"></i></button>
                        </div>
                    </form>
                    <div class="ml-sm-2 mr-sm-2">
                        Free form places
                    </div>
                    <form class="mb-sm-0" action="controller" method="get">
                        <div class="button">
                            <input type="hidden" name="command" value="sortByFreePlaceFacultiesReverse">
                            <button class=" btn btn-sm " type="submit"><i class="fas fa-sort-numeric-up"></i></button>
                        </div>
                    </form>
                </div>
            </th>
            <th class="align-middle">
                <div class="row d-flex justify-content-sm-center">
                    <form class="mb-sm-0" action="controller" method="get">
                        <div class="button">
                            <input type="hidden" name="command" value="sortByPayPlaceFaculties">
                            <button class=" btn btn-sm " type="submit"><i class="fas fa-sort-numeric-down"></i></button>
                        </div>
                    </form>
                    <div class="ml-sm-2 mr-sm-2"> Pay form places
                    </div>
                    <form class="mb-sm-0" action="controller" method="get">
                        <div class="button">
                            <input type="hidden" name="command" value="sortByPayPlaceFacultiesReverse">
                            <button class=" btn btn-sm " type="submit"><i class="fas fa-sort-numeric-up"></i></button>
                        </div>
                    </form>
                </div>
            </th>
            <th class="align-middle">
                <div class="row d-flex justify-content-sm-center">
                    <form class="mb-sm-0" action="controller" method="get">
                        <div class="button">
                            <input type="hidden" name="command" value="sortByAllPlaceFaculty">
                            <button class=" btn btn-sm " type="submit"><i class="fas fa-sort-numeric-down"></i></button>
                        </div>
                    </form>
                    <div class="ml-sm-2 mr-sm-2">
                        all places
                    </div>
                    <form class="mb-sm-0" action="controller" method="get">
                        <div class="button">
                            <input type="hidden" name="command" value="sortByAllPlaceFacultyReverse">
                            <button class=" btn btn-sm " type="submit"><i class="fas fa-sort-numeric-up"></i></button>
                        </div>
                    </form>
                </div>
            </th>
            <th>apply to faculties</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${sessionScope.facultiesList}" varStatus="counter" var="facultiesList">
        <c:if test="${facultiesList.id>1}">
        <tr>
            <td class="font-weight-bold align-middle">${facultiesList.name} </td>
            <td class="align-middle">${facultiesList.freeFormPlaces}</td>
            <td class="align-middle">${facultiesList.payFormPlaces}</td>
            <td class="align-middle">${facultiesList.allPlace}</td>
            <td class="align-middle">
                <c:if test="${sessionScope.facultiesExistInOrder!=facultiesList.id}">
                    <form action="controller" method="post">
                        <div class="button">
                            <input type="hidden" name="account_id" value="${sessionScope.account.id}">
                            <input type="hidden" name="faculties_id" value="${facultiesList.id}"/>
                            <input type="hidden" name="command" value="addOrder"/>
                            <button class="btn btn-sm" type="submit"> apply to faculties</button>
                        </div>
                    </form>
                </c:if>
                <c:if test="${sessionScope.facultiesExistInOrder==facultiesList.id}">
                    <h1> order exists</h1>
                </c:if>
            </td>
        </tr>
        </c:if>
        </c:forEach>
        <tbody>
    </table>
    <c:remove var="facultiesExistInOrder" scope="session"/>
</div>
<body>
<html>