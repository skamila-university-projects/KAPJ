<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><spring:message code="title.doctor24"/></title>
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons"
          rel="stylesheet">
</head>
<body>
<h3><spring:message code="menu.visits"/></h3>

<table class="table">
    <thead>
    <tr>
        <th scope="col"><spring:message code="date"/></th>
        <th scope="col"><spring:message code="doctor"/></th>
        <th scope="col"><spring:message code="patient"/></th>
        <th scope="col"><spring:message code="pesel"/></th>
        <th scope="col"></th>
        <th scope="col"></th>
        <th scope="col"></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${visits}" var="visit">
        <tr>
            <td><c:out value="${visit.time}"/></td>
            <td><c:out value="${visit.doctor.firstName}"/> <c:out value="${visit.doctor.lastName}"/></td>
            <td><c:out value="${visit.patient.firstName}"/> <c:out value="${visit.patient.lastName}"/></td>
            <td></td>
            <td>
                <c:choose>
                    <c:when test="${visit.canceled}">
                        <spring:message code="canceled"/>
                    </c:when>
                    <c:when test="${visit.confirmed}"><spring:message code="confirmed"/> </c:when>
                    <c:otherwise>
                        <spring:message code="unconfirmed"/>
                    </c:otherwise>
                </c:choose>
            </td>
            <td>
                <c:if test="${not visit.confirmed}"><span class="material-icons">check</span> </c:if>
                <c:if test="${not visit.canceled}">
                    <span class="material-icons">close</span>
                </c:if>
            </td>
            <td><span class="material-icons">request_quote</span></td>
        </tr>
    </c:forEach>
    </tbody>
</table>

</body>
</html>
