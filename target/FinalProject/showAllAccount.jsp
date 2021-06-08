<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Show accounts</title>
    <my:bootstrapLink/>
    <link rel="stylesheet" href="style/main.css">
    <script src="sort.js"></script>
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
        <my:shwoAllAccountButton/>
        <div class="pl-sm-3 pt-sm-3">
            <form action="addFaculties.jsp">
                <div class="button">
                    <button class="btn btn-sm" type="submit">Add Faculties</button>
                </div>
            </form>
        </div>
        <div class="pl-sm-3 pt-sm-3">
            <form action="admin.jsp" method="get">
                <div class="button">
                    <button class="btn btn-sm" type="submit">show all faculties</button>
                </div>
            </form>
        </div>
    </nav>
</header>
<div>
    <table border="1" class=" table table-hover table_sort text-center text-white my-table-class">
        <thead>
        <tr>
            <th>Login</th>
            <th>First name</th>
            <th>Middle name</th>
            <th>Last name</th>
            <th>eMail</th>
            <th>Certificate</th>
            <th>Role</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${sessionScope.accountList}" var="accountList">
            <c:if test="${accountList.id>1}">
                <tr>
                    <td>${accountList.login} </td>
                    <td>${accountList.firstName}</td>
                    <td>${accountList.middleName}</td>
                    <td>${accountList.lastName}</td>
                    <td>${accountList.EMail}</td>
                    <td>
                            <%--account certificate--%>
                        <form action="controller" method="get">
                            <div class="button">
                                <input type="hidden" name="firstName" value="${accountList.firstName}"/>
                                <input type="hidden" name="lastName" value="${accountList.lastName}"/>
                                <input type="hidden" name="middleName" value="${accountList.middleName}"/>
                                <input type="hidden" name="certificate_id" value="${accountList.certificate_id}"/>
                                <input type="hidden" name="command" value="findAccountCertificate"/>
                                <button class="btn btn-sm" type="submit"> show certificate</button>
                            </div>
                        </form>
                    </td>
                    <td>
                            <%--status account--%>
                        <div>
                            <form action="controller" method="post">
                                <label>
                                    <select class="form-control-sm " name="role_id">
                                        <c:forEach var="item" items="${sessionScope.roleList}">
                                            <option value="${item.ordinal()+1}"
                                                    <c:if test="${item.ordinal()+1 == accountList.role_id}">selected</c:if>>
                                                    ${item.name()}</option>
                                        </c:forEach>
                                    </select>
                                    <input type="hidden" name="login" value="${accountList.login}"/>
                                    <input type="hidden" name="password" value="${accountList.password}"/>
                                    <input type="hidden" name="firstName" value="${accountList.firstName}"/>
                                    <input type="hidden" name="lastName" value="${accountList.lastName}"/>
                                    <input type="hidden" name="middleName" value="${accountList.middleName}"/>
                                    <input type="hidden" name="email" value="${accountList.EMail}"/>
                                    <input type="hidden" name="city" value="${accountList.city}"/>
                                    <input type="hidden" name="region" value="${accountList.region}"/>
                                    <input type="hidden" name="studyForm" value="${accountList.studyForm_id}"/>
                                    <input type="hidden" name="certificate_id" value="${accountList.certificate_id}"/>
                                    <input type="hidden" name="faculties_name" value="${accountList.faculties_name}"/>
                                    <input type="hidden" name="account_id" value="${accountList.id}"/>
                                    <input type="hidden" name="command" value="updateAccount"/>
                                    <button class="btn btn-sm" type="submit"> update role</button>
                                </label>
                            </form>
                        </div>
                    </td>
                    <td>
                        <c:if test="${ sessionScope.accountIdActExists != accountList.id }">
                            <form action="controller" method="post">
                                <div class="button">
                                    <input type="hidden" name="firstName" value="${accountList.firstName}"/>
                                    <input type="hidden" name="lastName" value="${accountList.lastName}"/>
                                    <input type="hidden" name="middleName" value="${accountList.middleName}"/>
                                    <input type="hidden" name="account_id" value="${accountList.id}"/>
                                    <input type="hidden" name="studyForm_id" value="${accountList.studyForm_id}"/>
                                    <input type="hidden" name="certificate_id" value="${accountList.certificate_id}"/>
                                    <input type="hidden" name="faculties_name" value="${accountList.faculties_name}"/>
                                    <input type="hidden" name="command" value="findAccountOrders"/>
                                    <button class="btn btn-sm" type="submit">Create an act</button>
                                </div>
                            </form>
                        </c:if>
                        <c:if test="${not empty sessionScope.actExists
                        && sessionScope.accountIdActExists == accountList.id}">
                            <h1 class="d-flex justify-content-sm-center">${sessionScope.actExists}</h1>
                            <c:remove var="actExists" scope="session"/>
                            <c:remove var="accountIdActExists" scope="session"/>
                        </c:if>
                    </td>
                </tr>
            </c:if>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
