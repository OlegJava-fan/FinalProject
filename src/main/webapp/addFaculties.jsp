<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Add Faculties</title>
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
    </nav>
</header>

<body>

<div class=" mt-sm-3">
    <div class="container-fluid mt-sm-3">
        <div class="col-sm-12 d-flex justify-content-sm-center"><h3> *Not empty fields</h3>
        </div>
    </div>
    <div class="mt-sm-3">
        <form class="container-fluid text-white" action="controller" method="post">

            <div class="row">
                <label class="col-sm-5 d-flex justify-content-sm-end" for="name">Name Faculties:</label>
                <div class="col-sm-3">
                    <input type="text" id="name" name="name">
                    <label for="name">*</label>
                </div>
                <div class="col-sm-4">
                    <c:if test="${not empty sessionScope.mapNotValidFields}">
                        <c:forEach items="${sessionScope.mapNotValidFields}" var="error">
                            <c:if test="${error.key=='name'}">
                                <c:out value="${error.value}"/>
                                <br>

                            </c:if>
                        </c:forEach>
                    </c:if>
                </div>
            </div>
            <div class="row">
                <label class="col-sm-5 d-flex justify-content-sm-end" for="passingScoreFreeForm">Passing Score Free
                    Form:</label>
                <div class="col-sm-3">
                    <input type="text" id="passingScoreFreeForm" name="passingScoreFreeForm">
                    <label for="passingScoreFreeForm">*</label>
                </div>
                <div class="col-sm-4">
                    <c:if test="${not empty sessionScope.mapNotValidFields}">
                        <c:forEach items="${sessionScope.mapNotValidFields}" var="error">
                            <c:if test="${error.key=='passingScoreFreeForm'}">
                                <c:out value="${error.value}"/><br>
                            </c:if>
                        </c:forEach>
                    </c:if>
                </div>
            </div>
            <div class="row">
                <label class="col-sm-5 d-flex justify-content-sm-end" for="passingScorePayForm">Passing Score Pay
                    Form:</label>
                <div class="col-sm-3">
                    <input type="text" id="passingScorePayForm" name="passingScorePayForm">
                    <label for="passingScorePayForm">*</label>
                </div>
                <div class="col-sm-4">
                    <c:if test="${not empty sessionScope.mapNotValidFields}">
                        <c:forEach items="${sessionScope.mapNotValidFields}" var="error">
                            <c:if test="${error.key=='passingScorePayForm'}">
                                <c:out value="${error.value}"/><br>
                            </c:if>
                        </c:forEach>
                    </c:if>
                </div>
            </div>
            <div class="row">
                <label class="col-sm-5 d-flex justify-content-sm-end" for="allPlaces">All Places:</label>
                <div class="col-sm-3">
                    <input type="text" id="allPlaces" name="allPlaces">
                    <label for="allPlaces">*</label>
                </div>
                <div class="col-sm-4">
                    <c:if test="${not empty sessionScope.mapNotValidFields}">
                        <c:forEach items="${sessionScope.mapNotValidFields}" var="error">
                            <c:if test="${error.key=='allPlaces'}">
                                <c:out value="${error.value}"/><br>
                            </c:if>
                        </c:forEach>
                    </c:if>
                </div>
            </div>
            <div class="row">
                <label class="col-sm-5 d-flex justify-content-sm-end" for="freeFormPlaces">Free Form Places:</label>
                <div class="col-sm-3">
                    <input type="text" id="freeFormPlaces" name="freeFormPlaces">
                    <label for="freeFormPlaces">*</label>
                </div>
                <div class="col-sm-4">
                    <c:if test="${not empty sessionScope.mapNotValidFields}">
                        <c:forEach items="${sessionScope.mapNotValidFields}" var="error">
                            <c:if test="${error.key=='freeFormPlaces'}">
                                <c:out value="${error.value}"/><br>
                            </c:if>
                        </c:forEach>
                    </c:if>
                </div>
            </div>
            <div class="row">
                <label class="col-sm-5 d-flex justify-content-sm-end" for="payFormPlaces">Pay Form Places:</label>
                <div class="col-sm-3">
                    <input type="text" id="payFormPlaces" name="payFormPlaces">
                    <label for="payFormPlaces">*</label>
                </div>
                <div class="col-sm-4">
                    <c:if test="${not empty sessionScope.mapNotValidFields}">
                        <c:forEach items="${sessionScope.mapNotValidFields}" var="error">
                            <c:if test="${error.key=='payFormPlaces'}">
                                <c:out value="${error.value}"/><br>
                            </c:if>
                        </c:forEach>
                    </c:if>
                </div>
            </div>
            <div class="row">
                <div class="container-fluid ">
                    <div class="button d-flex justify-content-sm-center">
                        <input type="hidden" name="command" value="addFaculties"/>
                        <button class="btn btn-sm" type="submit">Add new faculties</button>
                    </div>
                </div>
            </div>
        </form>
    </div>
    <c:remove var="mapNotValidFields" scope="session"/>
</div>
</body>
</html>
