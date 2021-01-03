<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><tiles:insertAttribute name="title" ignore="true"/></title>
</head>
<body>
<table border="1" cellpadding="2" cellspacing="2" align="center">
    <tr><tiles:insertAttribute name="menu"/></tr>
    <tr><tiles:insertAttribute name="body"/></tr>
</table>
</body>
</html>
