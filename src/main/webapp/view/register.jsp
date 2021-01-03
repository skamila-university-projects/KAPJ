<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><spring:message code="title.doctor24"/></title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
            integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
            crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
            integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
            crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
            integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
            crossorigin="anonymous"></script>
    <script src="https://www.google.com/recaptcha/api.js"></script>
</head>
<body>

<h3><spring:message code="register"/></h3>
<form:form method="post" action="addPatient.html" modelAttribute="appUser" style="padding-top: 5px">
    <div class="form-row">
        <div class="col">
            <div class="form-group">
                <form:label path="login"><label for="login"><spring:message code="login"/></label></form:label>
                <form:input path="login" class="form-control" id="login"/>
                <form:errors path="login" class="form-text text-muted"/>
            </div>
        </div>
        <div class="col">
            <div class="form-group">
                <form:label path="password"><label for="password"><spring:message code="password"/></label></form:label>
                <form:input path="password" type="password" class="form-control" id="password"/>
                <form:errors path="password" class="form-text text-muted"/>
            </div>
        </div>
    </div>
    <div class="form-row">
        <div class="col">
            <div class="form-group">
                <form:label path="firstName"><label for="firstName"><spring:message
                        code="firstName"/></label></form:label>
                <form:input path="firstName" class="form-control" id="firstName"/>
                <form:errors path="firstName" class="form-text text-muted"/>
            </div>
        </div>
        <div class="col">
            <div class="form-group">
                <form:label path="lastName"><label for="lastName"><spring:message code="lastName"/></label></form:label>
                <form:input path="lastName" class="form-control" id="lastName"/>
                <form:errors path="lastName" class="form-text text-muted"/>
            </div>
        </div>
    </div>
    <div class="form-group">
        <form:label path="email"><label for="email"><spring:message code="email"/></label></form:label>
        <form:input path="email" class="form-control" id="email"/>
        <form:errors path="email" class="form-text text-muted"/>
    </div>
    <div class="form-group">
        <form:label path="phoneNumber"><label for="phoneNumber"><spring:message
                code="phoneNumber"/></label></form:label>
        <form:input path="phoneNumber" class="form-control" id="phoneNumber"/>
        <form:errors path="phoneNumber" class="form-text text-muted"/>
    </div>
    <div class="form-group">
        <form:label path="pesel.PESEL"><label for="pesel"><spring:message code="pesel"/></label></form:label>
        <form:input path="pesel.PESEL" class="form-control" id="pesel"/>
        <form:errors path="pesel.PESEL" class="form-text text-muted"/>
    </div>
    <div class="form-group">
        <div class="g-recaptcha" data-sitekey="6LdB8yIaAAAAACve7JMFskBBWcMNtstxFIV7eQEC"></div>
    </div>
    <button type="submit" class="btn btn-info"><spring:message code="register"/></button>
</form:form>

</body>
</html>
