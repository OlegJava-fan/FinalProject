<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Update Faculties</title>
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
    <form class="container-fluid text-white" action="controller" method="post">
        <div class="row">
            <div class="col-sm-12 d-flex justify-content-sm-center">
                <h2>Update faculty</h2>
            </div>
        </div>
        <div class="row">
            <label class="col-sm-5 d-flex justify-content-sm-end" for="name">Name Faculties:</label>
            <div class="col-sm-3">
                <input type="text" id="name" name="name" placeholder="${param.name}">
            </div>
            <div class="col-sm-4">
                <c:if test="${not empty sessionScope.mapNotValidToUpdateFields}">
                    <c:forEach items="${sessionScope.mapNotValidToUpdateFields}" var="error">
                        <c:if test="${error.key=='name'}">
                            <c:out value="${error.value}"/>
                            <br>
                        </c:if>
                    </c:forEach>
                </c:if>
            </div>
        </div>
        <div class="row">
            <label class="col-sm-5 d-flex justify-content-sm-end" for="passingScoreFreeForm">Passing score free
                form:</label>
            <div class="col-sm-3">
                <input type="text" id="passingScoreFreeForm" name="passingScoreFreeForm"
                       placeholder="${param.passingScoreFreeForm}">
            </div>
            <div class="col-sm-4">
                <c:if test="${not empty sessionScope.mapNotValidToUpdateFields}">
                    <c:forEach items="${sessionScope.mapNotValidToUpdateFields}" var="error">
                        <c:if test="${error.key=='passingScoreFreeForm'}">
                            <c:out value="${error.value}"/>
                            <br>
                        </c:if>
                    </c:forEach>
                </c:if>
            </div>
        </div>
        <div class="row">
            <label class="col-sm-5 d-flex justify-content-sm-end" for="passingScorePayForm">Passing score pay
                form:</label>
            <div class="col-sm-3">
                <input type="text" id="passingScorePayForm" name="passingScorePayForm"
                       placeholder="${param.passingScorePayForm}">
            </div>
            <div class="col-sm-4">
                <c:if test="${not empty sessionScope.mapNotValidToUpdateFields}">
                    <c:forEach items="${sessionScope.mapNotValidToUpdateFields}" var="error">
                        <c:if test="${error.key=='passingScorePayForm'}">
                            <c:out value="${error.value}"/>
                            <br>
                        </c:if>
                    </c:forEach>
                </c:if>
            </div>
        </div>
        <div class="row">
            <label class="col-sm-5 d-flex justify-content-sm-end" for="allPlaces">All places:</label>
            <div class="col-sm-3">
                <input type="text" id="allPlaces" name="allPlaces" placeholder="${param.allPlace}">
            </div>
            <div class="col-sm-4">
                <c:if test="${not empty sessionScope.mapNotValidToUpdateFields}">
                    <c:forEach items="${sessionScope.mapNotValidToUpdateFields}" var="error">
                        <c:if test="${error.key=='allPlaces'}">
                            <c:out value="${error.value}"/>
                            <br>
                        </c:if>
                    </c:forEach>
                </c:if>
            </div>
        </div>
        <div class="row">
            <label class="col-sm-5 d-flex justify-content-sm-end" for="freeFormPlaces">Free form places:</label>
            <div class="col-sm-3">
                <input type="text" id="freeFormPlaces" name="freeFormPlaces" placeholder="${param.freeFormPlaces}">
            </div>
            <div class="col-sm-4">
                <c:if test="${not empty sessionScope.mapNotValidToUpdateFields}">
                    <c:forEach items="${sessionScope.mapNotValidToUpdateFields}" var="error">
                        <c:if test="${error.key=='freeFormPlaces'}">
                            <c:out value="${error.value}"/>
                            <br>
                        </c:if>
                    </c:forEach>
                </c:if>
            </div>
        </div>
        <div class="row">
            <label class="col-sm-5 d-flex justify-content-sm-end" for="payFormPlaces">Pay form places:</label>
            <div class="col-sm-3">
                <input type="text" id="payFormPlaces" name="payFormPlaces" placeholder="${param.payFormPlaces}">
            </div>
            <div class="col-sm-4">
                <c:if test="${not empty sessionScope.mapNotValidToUpdateFields}">
                    <c:forEach items="${sessionScope.mapNotValidToUpdateFields}" var="error">
                        <c:if test="${error.key=='payFormPlaces'}">
                            <c:out value="${error.value}"/>
                            <br>
                        </c:if>
                    </c:forEach>
                </c:if>
            </div>
        </div>
        <div class="row">
            <div class="button col-sm-5 mt-sm-1 d-flex justify-content-sm-end">
                <c:if test="${not empty param.faculties_id}">
                    <input type="hidden" name="faculties_id" value="${param.faculties_id}">
                </c:if>

                <input type="hidden" name="command" value="updateFaculties"/>
                <button class="btn btn-sm" type="submit">Apply</button>
            </div>
        </div>
    </form>
    <c:remove var="mapNotValidToUpdateFields" scope="session"/>
</div>
<my:bootstrapScript/>
</body>
</html>
