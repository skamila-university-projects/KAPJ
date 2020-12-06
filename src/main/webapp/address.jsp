<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
      rel="stylesheet">
<head>
    <title><spring:message code="label.address"/></title>
</head>
<body>
<h1><spring:message code="label.address"/></h1>
<form:form method="post" action="addAddress.html" modelAttribute="address">

    <table>
        <tr>
            <td><form:hidden path="id"/></td>
        </tr>
        <tr>
            <td><form:label path="city"><spring:message code="label.address.city"/></form:label></td>
            <td><form:input path="city"/></td>
            <td><form:errors path="city"/></td>
        </tr>
        <tr>
            <td><form:label path="postalCode"><spring:message code="label.address.postalCode"/></form:label></td>
            <td><form:input path="postalCode"/></td>
            <td><form:errors path="postalCode"/></td>
        </tr>
        <tr>
            <td><form:label path="street"><spring:message code="label.address.street"/></form:label></td>
            <td><form:input path="street"/></td>
            <td><form:errors path="street"/></td>
        </tr>
        <tr>
            <td><form:label path="houseNumber"><spring:message code="label.address.houseNumber"/></form:label></td>
            <td><form:input path="houseNumber"/></td>
            <td><form:errors path="houseNumber"/></td>
        </tr>
        <tr>
            <td><form:label path="flatNumber"><spring:message code="label.address.flatNumber"/></form:label></td>
            <td><form:input path="flatNumber"/></td>
            <td><form:errors path="flatNumber"/></td>
        </tr>
        <tr>
            <td colspan="2">
                <c:if test="${address.id==0}">
                    <input type="submit" value="<spring:message code="label.address.add"/>"/>
                </c:if>
                <c:if test="${address.id!=0}">
                    <input type="submit" value="<spring:message code="label.address.edit"/>"/>
                </c:if>
            </td>
        </tr>
    </table>
    <c:if test="${!empty addressList}">
        <h3><spring:message code="label.addressList"/></h3>
        <table class="data">
            <tr>
                <th><spring:message code="label.firstName"/></th>
                <th><spring:message code="label.lastName"/></th>
                <th><spring:message code="label.email"/></th>
                <th><spring:message code="label.telephone"/></th>
                <th><spring:message code="label.telephone"/></th>
                <th>&nbsp;</th>
                <th>&nbsp;</th>
            </tr>
            <c:forEach items="${addressList}" var="address">
                <tr>
                    <td>${address.city} </td>
                    <td>${address.postalCode} </td>
                    <td>${address.street}</td>
                    <td>${address.houseNumber}</td>
                    <td>${address.flatNumber}</td>
                    <td><a href="address.html?addressId=${address.id}"><span class="material-icons">edit</span></a>
                    </td>
                    <td><a href="deleteAddress/${address.id}.html"><span class="material-icons">delete</span></a></td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
</form:form>
</body>
</html>
