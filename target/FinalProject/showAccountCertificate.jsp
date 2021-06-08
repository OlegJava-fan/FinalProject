
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Account certificate</title>
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
        <div class="pl-sm-3 pt-sm-3">
            <form action="admin.jsp" method="get">
                <div class="button">
                    <button class="btn btn-sm" type="submit">show all faculties</button>
                </div>
            </form>
        </div>
    </nav>
</header>
<body>
<div class="container-fluid">
    <div class="row pt-sm-3">
        <div class="col-sm-12 d-flex justify-content-sm-center ">
            <h2>${param.firstName} ${param.middleName} ${param.lastName} </h2>
        </div>
    </div>
    <div class="row d-flex justify-content-sm-center pt-sm-3">
        <div class=" col-sm-3 d-flex justify-content-sm-center">
            <table border="1"
                   class=" table table-sm table-hover text-center text-white my-table-class">

                <tr>
                    <th> mathematics</th>
                    <th>${requestScope.certificate.mathematics} </th>
                </tr>
                <tr>
                    <th>chemistry</th>
                    <th>${requestScope.certificate.chemistry}</th>
                </tr>
                <tr>
                    <th>physics</th>
                    <th>${requestScope.certificate.physics}</th>
                </tr>
                <tr>
                    <th>english</th>
                    <th>${requestScope.certificate.english}</th>
                </tr>
                <tr>
                    <th>ukrainian</th>
                    <th>${requestScope.certificate.ukrainian}</th>
                </tr>
                <tr>
                    <th>informatics</th>
                    <th>${requestScope.certificate.informatics}</th>
                </tr>
                <tr>
                    <th>geography</th>
                    <th>${requestScope.certificate.geography}</th>
                </tr>
                <tr>
                    <th>biology</th>
                    <th>${requestScope.certificate.biology}</th>
                </tr>
                <tr>
                    <th>history</th>
                    <th>${requestScope.certificate.history}</th>
                </tr>
                <tr>
                    <th>gym</th>
                    <th>${requestScope.certificate.gym}</th>
                </tr>
                <tr>
                    <th>average score</th>
                    <th>${requestScope.certificate.averageScore}</th>
                </tr>
            </table>
        </div>
    </div>
</div>
</body>
</html>
