<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<html>
<head>
    <meta charset="UTF-8">
    <title>Admin Page</title>
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
        <my:shwoAllAccountButton/>
        <div class="pl-sm-3 pt-sm-3">
            <form action="addFaculties.jsp">
                <div class="button">
                    <button class="btn btn-sm" type="submit">Add Faculties</button>
                </div>
            </form>
        </div>
    </nav>
</header>
<body>
<div>
    <table border="1" class=" table table-hover text-center text-white ">
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
                    <div class="ml-sm-2 mr-sm-2">
                        Pay form places
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
                        All places
                    </div>
                    <form class="mb-sm-0" action="controller" method="get">
                        <div class="button">
                            <input type="hidden" name="command" value="sortByAllPlaceFacultyReverse">
                            <button class=" btn btn-sm " type="submit"><i class="fas fa-sort-numeric-up"></i></button>
                        </div>
                    </form>
                </div>

            </th>
            <th>Delete faculties</th>
            <th>Update faculties</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${sessionScope.facultiesList}" var="facultiesList">
            <c:if test="${facultiesList.id>1}">
                <tr>
                    <td class="font-weight-bold">${facultiesList.name} </td>
                    <td>${facultiesList.freeFormPlaces}</td>
                    <td>${facultiesList.payFormPlaces}</td>
                    <td>${facultiesList.allPlace}</td>
                    <td>
                        <form action="controller" method="post">
                            <div class="button">
                                <input type="hidden" name="faculties_id" value="${facultiesList.id}"/>
                                <input type="hidden" name="command" value="deleteFaculties"/>
                                <button class="btn btn-sm" type="submit">delete faculties</button>
                            </div>
                        </form>
                    </td>
                    <td>
                        <form action="updateFaculties.jsp" method="get">
                            <div class="button">
                                <input type="hidden" name="name" value="${facultiesList.name}"/>
                                <input type="hidden" name="passingScorePayForm"
                                       value="${facultiesList.passingScorePayForm}"/>
                                <input type="hidden" name="passingScoreFreeForm"
                                       value="${facultiesList.passingScoreFreeForm}"/>
                                <input type="hidden" name="allPlace" value="${facultiesList.allPlace}"/>
                                <input type="hidden" name="freeFormPlaces" value="${facultiesList.freeFormPlaces}"/>
                                <input type="hidden" name="payFormPlaces" value="${facultiesList.payFormPlaces}"/>
                                <input type="hidden" name="faculties_id" value="${facultiesList.id}"/>
                                <button class="btn btn-sm" type="submit">update faculties</button>
                            </div>
                        </form>
                    </td>
                </tr>
            </c:if>
        </c:forEach>
        </tbody>
    </table>
</div>
<my:bootstrapScript/>
<body>
<html>