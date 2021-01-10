<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>

</head>
<body>

<script>
    function formSubmit() {
        document.getElementById("logoutForm").submit();
    }
</script>

<form action="/logout" method="post" id="logoutForm">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo01"
            aria-controls="navbarTogglerDemo01" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse">
        <a class="navbar-brand" href="#"><spring:message code="title.doctor24"/></a>
        <ul class="navbar-nav mr-auto mt-2 mt-lg-0">

            <%-- Pacjeńci --%>
            <security:authorize access="hasRole('ROLE_PATIENT')">
                <li class="nav-item nav-link">
                    <a class="nav-link" href="/visit/new"><spring:message code="menu.newVisit"/></a>
                </li>
                <li class="nav-item nav-link">
                    <a class="nav-link" href="/visit/my"><spring:message code="menu.myVisits"/></a>
                </li>
            </security:authorize>

            <%-- Lekarze --%>
            <security:authorize access="hasRole('ROLE_DOCTOR')">
                <li class="nav-item nav-link">
                    <a class="nav-link" href="/visit/doctor"><spring:message code="menu.visits"/></a>
                </li>
            </security:authorize>

            <%-- Administrator --%>
            <security:authorize access="hasRole('ROLE_ADMIN')">
                <li class="nav-item nav-link">
                    <a class="nav-link" href="/visit/admin"><spring:message code="menu.visits"/></a>
                </li>
                <li class="nav-item nav-link">
                    <a class="nav-link" href="/admin/register"><spring:message code="menu.addUser"/></a>
                </li>
            </security:authorize>

            <%-- Niezarejestrowani użytkownicy --%>
            <security:authorize access="isAnonymous()">
                <li class="nav-item nav-link">
                    <a class="nav-link" href="register"><spring:message code="register"/></a>
                </li>
                <li class="nav-item nav-link">
                    <a class="nav-link" href="login"><spring:message code="logIn"/></a>
                </li>
            </security:authorize>
        </ul>

        <ul class="navbar-nav navbar-righ">
            <%-- Zarejestrowani użytkownicy --%>
            <security:authorize access="isAuthenticated()">
                <li class="nav-item nav-link">
                    <a class="nav-link" href="">${pageContext.request.userPrincipal.name}</a>
                </li>
                <li class="nav-item nav-link">
                    <a class="nav-link" href="javascript:formSubmit()"><spring:message code="logout"/></a>
                </li>
            </security:authorize>
        </ul>
        <ul class="navbar-nav navbar-righ">
            <div class="form-row">
                <div class="col">
                    <a class="language-button" style="color: white" href="?lang=pl"><button type="button" class="btn btn-outline-info btn-sm">PL</button></a>
                    <a class="language-button" style="color: white" href="?lang=en"><button type="button" class="btn btn-outline-info btn-sm">EN</button></a>
                    <a class="language-button" style="color: white" href="?lang=is"><button type="button" class="btn btn-outline-info btn-sm">IS</button></a>
                </div>
            </div>
        </ul>

    </div>
</nav>
</body>
</html>