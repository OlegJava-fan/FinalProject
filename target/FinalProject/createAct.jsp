<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>


<html>
<head>
    <title>Create act</title>
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
        <my:showAllFaculties/>
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
<c:choose>
<c:when test="${empty sessionScope.actSuccessfullyCreated && empty sessionScope.actsAlreadyExist}">
    <table border="1" class="table table-hover text-center text-white my-table-class">
        <tr>
            <th>first name</th>
            <th>middle name</th>
            <th>last name</th>
            <th>average score</th>
        </tr>
        <tr>
            <td>${requestScope.currentAccountInfo.firstName} </td>
            <td>${requestScope.currentAccountInfo.middleName}</td>
            <td>${requestScope.currentAccountInfo.lastName}</td>
            <td>${sessionScope.certificateAccount.averageScore}</td>
        </tr>
    </table>
    <table border="1" class="table table-hover text-center text-white my-table-class">
        <thead>
        <th>name faculties</th>
        <th>All place</th>
        <th>pay form places</th>
        <th>free form places</th>
        <th>passing score free form</th>
        <th>passing score pay form</th>
        <th>status order</th>
        </thead>
        <tbody>
        <c:forEach items="${sessionScope.facultiesListGroupByOrder}" var="item" varStatus="counterFaculties">
            <tr>
                <td class="font-weight-bold">${item.name}</td>
                <td>${item.allPlace}</td>
                <td>${item.payFormPlaces}</td>
                <td>${item.freeFormPlaces}</td>
                <td>${item.passingScoreFreeForm}</td>
                <td>${item.passingScorePayForm}</td>

                <td>
                    <form action="controller" method="get">
                        <label>
                            <select name="status_id">
                                <c:forEach var="status" items="${sessionScope.statusOrderList}" varStatus="counter">
                                    <option value="${status.ordinal()+1}"
                                            <c:if test="${status.ordinal()+1 == sessionScope.orderList.get(counterFaculties.index).status_id}">selected</c:if>>
                                            ${status.name()}
                                    </option>
                                </c:forEach>
                            </select>

                            <input type="hidden" name="id"
                                   value="${sessionScope.orderList.get(counterFaculties.index).id}"/>
                            <input type="hidden" name="account_id" value="${param.account_id}"/>
                            <input type="hidden" name="faculties_id" value="${item.id}"/>
                            <input type="hidden" name="firstName" value="${param.firstName}"/>
                            <input type="hidden" name="middleName" value="${param.middleName}"/>
                            <input type="hidden" name="lastName" value="${param.lastName}"/>
                            <input type="hidden" name="averageScore"
                                   value="${sessionScope.certificateAccount.averageScore}">
                            <input type="hidden" name="command" value="updateOrder"/>
                            <button class="button btn btn-sm" type="submit"> update status</button>
                        </label>
                    </form>
                </td>

            </tr>
        </c:forEach>
        </tbody>
    </table>
    <div class="button ml-sm-3">
        <form action="controller" method="post">
            <input type="hidden" name="account_id" value="${param.account_id}"/>
            <input type="hidden" name="command" value="addAct"/>
            <button class="btn btn-sm" type="submit"> submit act</button>
        </form>
    </div>
</c:when>
<c:when test="${not empty sessionScope.actSuccessfullyCreated && empty sessionScope.actsAlreadyExist}">
    <div class="container-fluid mt-sm-3">
        <div class="row">
            <div class="col-sm-12">
                <h2 class="d-flex justify-content-sm-center">${sessionScope.actSuccessfullyCreated}</h2>
            </div>
        </div>
    </div>

</c:when>

<c:when test="${not empty sessionScope.actsAlreadyExist}">
    <div class="container-fluid mt-sm-3">
        <div class="row">
            <div class="col-sm-12">
                <h2 class="d-flex justify-content-sm-center">${sessionScope.actsAlreadyExist}</h2>
            </div>
        </div>
    </div>

</c:when>
</c:choose>
</body>
</html>
