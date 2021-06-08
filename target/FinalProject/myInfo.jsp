<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>My certificate</title>
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
        <my:myOrdersButton/>
        <my:myResultButton/>

    </nav>
</header>
<body>
<div class="container-fluid">
    <div class="row pt-sm-3">
        <div class="col-sm-12 d-flex justify-content-sm-center ">
            <table border="1"
                   class=" table table-sm table-hover text-center text-white my-table-class">
                <tr>
                    <th>first name</th>
                    <th>middle name</th>
                    <th>last name</th>
                    <th>city</th>
                    <th>region</th>
                    <th>eMail</th>
                </tr>
                <tr>
                    <td>${param.firstName}</td>
                    <td>${param.middleName}</td>
                    <td>${param.lastName}</td>
                    <td>${param.city}</td>
                    <td>${param.region}</td>
                    <td>${param.eMail}</td>
                </tr>

            </table>
        </div>
    </div>
    <div class="row d-flex justify-content-sm-center pt-sm-3">
        <div class=" col-sm-3 d-flex justify-content-sm-center">
            <table border="1"
                   class=" table table-sm table-hover text-center text-white my-table-class">

                <tr>
                    <th> mathematics</th>
                    <th>${requestScope.myCertificate.mathematics} </th>
                </tr>
                <tr>
                    <th>chemistry</th>
                    <th>${requestScope.myCertificate.chemistry}</th>
                </tr>
                <tr>
                    <th>physics</th>
                    <th>${requestScope.myCertificate.physics}</th>
                </tr>
                <tr>
                    <th>english</th>
                    <th>${requestScope.myCertificate.english}</th>
                </tr>
                <tr>
                    <th>ukrainian</th>
                    <th>${requestScope.myCertificate.ukrainian}</th>
                </tr>
                <tr>
                    <th>informatics</th>
                    <th>${requestScope.myCertificate.informatics}</th>
                </tr>
                <tr>
                    <th>geography</th>
                    <th>${requestScope.myCertificate.geography}</th>
                </tr>
                <tr>
                    <th>biology</th>
                    <th>${requestScope.myCertificate.biology}</th>
                </tr>
                <tr>
                    <th>history</th>
                    <th>${requestScope.myCertificate.history}</th>
                </tr>
                <tr>
                    <th>gym</th>
                    <th>${requestScope.myCertificate.gym}</th>
                </tr>
                <tr>
                    <th>average score</th>
                    <th>${requestScope.myCertificate.averageScore}</th>
                </tr>
            </table>

        </div>
    </div>
</div>
</body>