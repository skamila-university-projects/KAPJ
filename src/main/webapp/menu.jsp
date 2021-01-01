<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="security"%>

<h1><spring:message code="label.menu"/></h1>

<security:authorize access="hasRole('ROLE_ADMIN')">
    <a href="/appUsers.html"><spring:message code="label.addAppUser"/></a><br/>
    <a href="/addresses.html"><spring:message code="label.address"/></a><br/>
    <a href="/appUserRole.html"><spring:message code = "label.role"/></a><br/>
</security:authorize>

<security:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_USER')">
    <a href="/exampleOne.jsp">Example 1</a><br/>
</security:authorize>

<security:authorize access="hasRole('ROLE_STUDENT')">
    <a href="/exampleTwo.jsp">Example 2</a><br/>
</security:authorize>

<security:authorize access="hasRole('ROLE_USER')">
    <a href="/exampleThree.jsp">Example 3</a><br/>
</security:authorize>

<script>
    function formSubmit() {
        document.getElementById("logoutForm").submit();
    }
</script>

<!-- csrf for log out-->
<form action="/logout" method="post" id="logoutForm">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
</form>

<br/>
<div>
    <security:authorize access="isAnonymous()">
        <a href="/login.html">Login</a><br/>
    </security:authorize>
    <security:authorize access="isAuthenticated()">
        <a href="javascript:formSubmit()"> Logout</a>
    </security:authorize>
</div>