<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Account act</title>
    <my:bootstrapLink/>
    <link rel="stylesheet" href="style/main.css">
</head>
<body>
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
        <my:myOrdersButton/>
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
<c:choose>
    <c:when test="${not empty sessionScope.alreadyAcceptedFaculty}">
        <div class="container-fluid mt-sm-3">
            <div class="col-sm-12 d-flex justify-content-sm-center">
                <h2>${sessionScope.alreadyAcceptedFaculty}</h2>
            </div>
        </div>

    </c:when>

    <c:when test="${not empty sessionScope.passedOnFacultiesStudyForm}">
        <div class="container-fluid mt-sm-3">
            <div class="col-sm-12 d-flex justify-content-sm-center">
                <h2>Congratulations, you are accepted to the faculty ${sessionScope.passedOnFacultiesName}.Your study
                    form ${sessionScope.passedOnFacultiesStudyForm} </h2>
            </div>
        </div>
        <div>
            <table border="1" class="table table-hover table_sort text-center text-white my-table-class">
                <thead>
                <tr>
                    <th>Faculties name</th>
                    <th>Study form</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td class="font-weight-bold">${sessionScope.passedOnFacultiesName} </td>
                    <td>${sessionScope.passedOnFacultiesStudyForm}</td>
                </tr>
                <tbody>
            </table>
        </div>
    </c:when>

    <c:otherwise>
        <div>
            <table border="1" class="table table-hover table_sort text-center text-white my-table-class">
                <thead>
                <tr>
                    <th>Faculties name</th>
                    <th>Status order</th>
                    <th>confirm admission</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${sessionScope.accountActList}" var="accountActList">
                <tr>
                    <td class="font-weight-bold">${accountActList.actFacultiesName} </td>
                    <td>${accountActList.status}</td>
                    <td>
                        <c:if test="${accountActList.status!='REJECT'}">
                            <form action="controller" method="post">
                                <div class="button">
                                    <input type="hidden" name="account_id" value="${sessionScope.account.id}">
                                    <input type="hidden" name="faculties_id" value="${accountActList.faculties_id}"/>
                                    <input type="hidden" name="status" value="${accountActList.status}"/>
                                    <div class="button">
                                        <input type="hidden" name="command" value="confirmAdmissionFaculties"/>
                                        <button class="btn btn-sm" type="submit">confirm admission</button>
                                    </div>
                                </div>
                            </form>
                        </c:if>
                    </td>
                </tr>
                </c:forEach>
                <tbody>
            </table>
        </div>
    </c:otherwise>

</c:choose>
<c:remove var="passedOnFacultiesStudyForm" scope="session"/>
<c:remove var="passedOnFacultiesName" scope="session"/>
<c:remove var="alreadyAcceptedFaculty" scope="session"/>
</body>
</html>
