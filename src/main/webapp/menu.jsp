<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h1><spring:message code="label.menu"/></h1>
<a href="/appUsers.html"><spring:message code="label.addAppUser"/></a><br/>
<a href="/addresses.html"><spring:message code="label.address"/></a><br/>

<a href="/exampleOne.jsp">Example 1</a><br/>
<a href="/exampleTwo.jsp">Example 2</a><br/>
<a href="/exampleThree.jsp">Example 3</a><br/>
<a href="/appUserRole.html"><spring:message code = "label.role"/></a>

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

    <c:choose>
        <c:when test="${empty pageContext.request.userPrincipal}">
            <a href="/login.html">Login</a><br/>
        </c:when>
        <c:otherwise>
            <spring:message code="label.welcome"/> : ${pageContext.request.userPrincipal.name} |
            <a href="javascript:formSubmit()"> Logout</a>
        </c:otherwise>
    </c:choose>

</div>