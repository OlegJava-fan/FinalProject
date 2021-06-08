<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Registration</title>
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
    <h3 class="col-sm-12 d-flex justify-content-sm-center">*not empty fields</h3>
</div>
<div class="mt-sm-3">
    <form class="container-fluid text-white" action="controller" method="post">
        <div class="row">
            <label class="col-sm-5 d-flex justify-content-sm-end" for="login">Login:</label>
            <div class="col-sm-3">
                <input type="text" id="login" name="login">
                <label for="login">*</label>
            </div>
            <div class="col-sm-4">
                <c:if test="${not empty sessionScope.notValidAccountField}">
                    <c:forEach items="${sessionScope.notValidAccountField}" var="error">
                        <c:if test="${error.key=='login'}">
                            <c:out value="${error.value}"/>
                            <br>
                        </c:if>
                    </c:forEach>
                </c:if>
            </div>
        </div>
        <div class="row">
            <label class="col-sm-5 d-flex justify-content-sm-end" for="password">Password:</label>
            <div class="col-sm-3">
                <input type="text" id="password" name="password">
                <label for="password">*</label>
            </div>
            <div class="col-sm-4">
                <c:if test="${not empty sessionScope.notValidAccountField}">
                    <c:forEach items="${sessionScope.notValidAccountField}" var="error">
                        <c:if test="${error.key=='password'}">
                            <c:out value="${error.value}"/>
                            <br>
                        </c:if>
                    </c:forEach>
                </c:if>
            </div>
        </div>
        <div class="row">
            <label class="col-sm-5 d-flex justify-content-sm-end" for="firstName"> First Name:</label>
            <div class="col-sm-3">
                <input type="text" id="firstName" name="firstName">
                <label for="firstName">*</label>
            </div>
            <div class="col-sm-4">
                <c:if test="${not empty sessionScope.notValidAccountField}">
                    <c:forEach items="${sessionScope.notValidAccountField}" var="error">
                        <c:if test="${error.key=='firstName'}">
                            <c:out value="${error.value}"/>
                            <br>
                        </c:if>
                    </c:forEach>
                </c:if>
            </div>
        </div>
        <div class="row">
            <label class="col-sm-5 d-flex justify-content-sm-end" for="lastName">Last Name:</label>
            <div class="col-sm-3">
                <input type="text" id="lastName" name="lastName">
                <label for="lastName">*</label>
            </div>
            <div class="col-sm-4">
                <c:if test="${not empty sessionScope.notValidAccountField}">
                    <c:forEach items="${sessionScope.notValidAccountField}" var="error">
                        <c:if test="${error.key=='lastName'}">
                            <c:out value="${error.value}"/>
                            <br>
                        </c:if>
                    </c:forEach>
                </c:if>
            </div>
        </div>
        <div class="row">
            <label class="col-sm-5 d-flex justify-content-sm-end" for="middleName">Middle Name:</label>
            <div class="col-sm-3">
                <input type="text" id="middleName" name="middleName">
                <label for="middleName">*</label>
            </div>
            <div class="col-sm-4">
                <c:if test="${not empty sessionScope.notValidAccountField}">
                    <c:forEach items="${sessionScope.notValidAccountField}" var="error">
                        <c:if test="${error.key=='middleName'}">
                            <c:out value="${error.value}"/>
                            <br>
                        </c:if>
                    </c:forEach>
                </c:if>
            </div>
        </div>
        <div class="row">
            <label class="col-sm-5 d-flex justify-content-sm-end" for="email">eMail:</label>
            <div class="col-sm-3">
                <input type="text" id="email" name="email">
                <label for="email">*</label>
            </div>
            <div class="col-sm-4">
                <c:if test="${not empty sessionScope.notValidAccountField}">
                    <c:forEach items="${sessionScope.notValidAccountField}" var="error">
                        <c:if test="${error.key=='email'}">
                            <c:out value="${error.value}"/>
                            <br>
                        </c:if>
                    </c:forEach>
                </c:if>
            </div>
        </div>
        <div class="row">
            <label class="col-sm-5 d-flex justify-content-sm-end" for="city">City:</label>
            <div class="col-sm-3">
                <input type="text" id="city" name="city">
                <label for="city">*</label>
            </div>
            <div class="col-sm-4">
                <c:if test="${not empty sessionScope.notValidAccountField}">
                    <c:forEach items="${sessionScope.notValidAccountField}" var="error">
                        <c:if test="${error.key=='city'}">
                            <c:out value="${error.value}"/>
                            <br>
                        </c:if>
                    </c:forEach>
                </c:if>
            </div>
        </div>
        <div class="row">
            <label class="col-sm-5 d-flex justify-content-sm-end" for="region">Region:</label>
            <div class="col-sm-3">
                <input type="text" id="region" name="region">
                <label for="region">*</label>
            </div>
            <div class="col-sm-4">
                <c:if test="${not empty sessionScope.notValidAccountField}">
                    <c:forEach items="${sessionScope.notValidAccountField}" var="error">
                        <c:if test="${error.key=='region'}">
                            <c:out value="${error.value}"/>
                            <br>
                        </c:if>
                    </c:forEach>
                </c:if>
            </div>
        </div>
        <div class="row mt-sm-1 mb-sm-1">
            <div class="col-sm-12 d-flex justify-content-sm-center">
                <h3>Enter your certificate data: </h3>
            </div>
        </div>

        <div class="row">
            <label class="col-sm-5 d-flex justify-content-sm-end" for="mathematics">mathematics:</label>
            <div class="col-sm-3">
                <input type="text" id="mathematics" name="mathematics">
                <label for="mathematics">*</label>
            </div>
            <div class="col-sm-4">
                <c:if test="${not empty sessionScope.notValidCertificateField}">
                    <c:forEach items="${sessionScope.notValidCertificateField}" var="error">
                        <c:if test="${error.key=='mathematics'}">
                            <c:out value="${error.value}"/>
                            <br>
                        </c:if>
                    </c:forEach>
                </c:if>
            </div>
        </div>
        <div class="row">
            <label class="col-sm-5 d-flex justify-content-sm-end" for="chemistry">chemistry:</label>
            <div class="col-sm-3">
                <input type="text" id="chemistry" name="chemistry">
                <label for="chemistry">*</label>
            </div>
            <div class="col-sm-4">
                <c:if test="${not empty sessionScope.notValidCertificateField}">
                    <c:forEach items="${sessionScope.notValidCertificateField}" var="error">
                        <c:if test="${error.key=='chemistry'}">
                            <c:out value="${error.value}"/>
                            <br>
                        </c:if>
                    </c:forEach>
                </c:if>
            </div>
        </div>
        <div class="row">
            <label class="col-sm-5 d-flex justify-content-sm-end" for="physics">physics:</label>
            <div class="col-sm-3">
                <input type="text" id="physics" name="physics">
                <label for="physics">*</label>
            </div>
            <div class="col-sm-4">
                <c:if test="${not empty sessionScope.notValidCertificateField}">
                    <c:forEach items="${sessionScope.notValidCertificateField}" var="error">
                        <c:if test="${error.key=='physics'}">
                            <c:out value="${error.value}"/>
                            <br>
                        </c:if>
                    </c:forEach>
                </c:if>
            </div>
        </div>
        <div class="row">
            <label class="col-sm-5 d-flex justify-content-sm-end" for="english">english:</label>
            <div class="col-sm-3">
                <input type="text" id="english" name="english">
                <label for="english">*</label>
            </div>
            <div class="col-sm-4">
                <c:if test="${not empty sessionScope.notValidCertificateField}">
                    <c:forEach items="${sessionScope.notValidCertificateField}" var="error">
                        <c:if test="${error.key=='english'}">
                            <c:out value="${error.value}"/>
                            <br>
                        </c:if>
                    </c:forEach>
                </c:if>
            </div>
        </div>
        <div class="row">
            <label class="col-sm-5 d-flex justify-content-sm-end" for="ukrainian">ukrainian:</label>
            <div class="col-sm-3">
                <input type="text" id="ukrainian" name="ukrainian">
                <label for="ukrainian">*</label>
            </div>
            <div class="col-sm-4">
                <c:if test="${not empty sessionScope.notValidCertificateField}">
                    <c:forEach items="${sessionScope.notValidCertificateField}" var="error">
                        <c:if test="${error.key=='ukrainian'}">
                            <c:out value="${error.value}"/>
                            <br>
                        </c:if>
                    </c:forEach>
                </c:if>
            </div>
        </div>
        <div class="row">
            <label class="col-sm-5 d-flex justify-content-sm-end" for="informatics">informatics:</label>
            <div class="col-sm-3">
                <input type="text" id="informatics" name="informatics">
                <label for="informatics">*</label>
            </div>
            <div class="col-sm-4">
                <c:if test="${not empty sessionScope.notValidCertificateField}">
                    <c:forEach items="${sessionScope.notValidCertificateField}" var="error">
                        <c:if test="${error.key=='informatics'}">
                            <c:out value="${error.value}"/>
                            <br>
                        </c:if>
                    </c:forEach>
                </c:if>
            </div>
        </div>
        <div class="row">
            <label class="col-sm-5 d-flex justify-content-sm-end" for="geography">geography:</label>
            <div class="col-sm-3">
                <input type="text" id="geography" name="geography">
                <label for="geography">*</label>
            </div>
            <div class="col-sm-4">
                <c:if test="${not empty sessionScope.notValidCertificateField}">
                    <c:forEach items="${sessionScope.notValidCertificateField}" var="error">
                        <c:if test="${error.key=='geography'}">
                            <c:out value="${error.value}"/>
                            <br>
                        </c:if>
                    </c:forEach>
                </c:if>
            </div>
        </div>
        <div class="row">
            <label class="col-sm-5 d-flex justify-content-sm-end" for="biology">biology:</label>
            <div class="col-sm-3">
                <input type="text" id="biology" name="biology">
                <label for="biology">*</label>
            </div>
            <div class="col-sm-4">
                <c:if test="${not empty sessionScope.notValidCertificateField}">
                    <c:forEach items="${sessionScope.notValidCertificateField}" var="error">
                        <c:if test="${error.key=='biology'}">
                            <c:out value="${error.value}"/>
                            <br>
                        </c:if>
                    </c:forEach>
                </c:if>
            </div>
        </div>
        <div class="row">
            <label class="col-sm-5 d-flex justify-content-sm-end" for="history">history:</label>
            <div class="col-sm-3">
                <input type="text" id="history" name="history">
                <label for="history">*</label>
            </div>
            <div class="col-sm-4">
                <c:if test="${not empty sessionScope.notValidCertificateField}">
                    <c:forEach items="${sessionScope.notValidCertificateField}" var="error">
                        <c:if test="${error.key=='history'}">
                            <c:out value="${error.value}"/>
                            <br>
                        </c:if>
                    </c:forEach>
                </c:if>
            </div>
        </div>
        <div class="row">
            <label class="col-sm-5 d-flex justify-content-sm-end" for="gym">gym:</label>
            <div class="col-sm-3">
                <input type="text" id="gym" name="gym">
                <label for="gym">*</label>
            </div>
            <div class="col-sm-4">
                <c:if test="${not empty sessionScope.notValidCertificateField}">
                    <c:forEach items="${sessionScope.notValidCertificateField}" var="error">
                        <c:if test="${error.key=='gym'}">
                            <c:out value="${error.value}"/>
                            <br>
                        </c:if>
                    </c:forEach>
                </c:if>
            </div>
        </div>
        <%--<div class="row mt-sm-1">
            <div class="col-sm-12 d-flex justify-content-sm-center button">
                <div class="custom-file">
                    <form action="controller" method="post"
                          enctype="multipart/form-data">
                        <input class="custom-file-input" id="inputFile" accept="image/*" type="file" name="file">
                        <input type="hidden" name="command" value="uploadCertificate">
                        <label class="custom-file-label" for="inputFile" data-browse="Upload scan">choose file</label>
                        <button class="btn btn-sm" type="submit" value="upload certificate">Send</button>
                    </form>
                </div>
            </div>
        </div>--%>
        <div class="row mt-sm-1">
            <div class="col-sm-12 d-flex justify-content-sm-center button">
                <input type="hidden" name="role_id" value="">
                <input type="hidden" name="studyForm" value="">
                <input type="hidden" name="faculties_name" value="">
                <input type="hidden" name="command" value="registration"/>
                <button class="btn btn-sm" type="submit">Registration</button>
            </div>
        </div>
    </form>
    <c:remove var="notValidCertificateField" scope="session"/>
    <c:remove var="notValidAccountField" scope="session"/>
</div>
<my:bootstrapScript/>
</body>
</html>
