<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
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
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
</form>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo01" aria-controls="navbarTogglerDemo01" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse">
        <a class="navbar-brand" href="#"><spring:message code="title.doctor24"/></a>
            <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
                <security:authorize access="isAuthenticated()">
                    <li class="nav-item nav-link">
                        <a class="nav-link" href="#"><spring:message code="menu.newVisit"/></a>
                    </li>
                    <li class="nav-item nav-link">
                        <a class="nav-link" href="#"><spring:message code="menu.visits"/></a>
                    </li>
                    <li class="nav-item nav-link">
                        <a class="nav-link" href="#"><spring:message code="menu.ustawienia"/></a>
                    </li>
                    <li class="nav-item nav-link">
                        <a class="nav-link" href="javascript:formSubmit()"><spring:message code="logout"/></a>
                    </li>
                    <li class="nav-item nav-link">
                        <a class="nav-link" href="">${pageContext.request.userPrincipal.name}</a>
                    </li>
                </security:authorize>
                <security:authorize access="isAnonymous()">
                    <li class="nav-item nav-link">
                        <a class="nav-link" href="register"><spring:message code="register"/></a>
                    </li>
                    <li class="nav-item nav-link">
                        <a class="nav-link" href="login"><spring:message code="logIn"/></a>
                    </li>
                </security:authorize>
            </ul>
    </div>
</nav>
</body>
</html>