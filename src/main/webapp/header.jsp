<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<link href="<c:url value="/resources/css/header.css" />" rel="stylesheet">

<div class="header">
    <h3><spring:message code="label.title"/></h3>
    <span style="float: right">
    	<a style="color: white" href="?lang=pl">PL</a> | <a style="color: white" href="?lang=en">EN</a> | <a
            style="color: white" href="?lang=is">IS</a>
	</span>
    <br>
</div>