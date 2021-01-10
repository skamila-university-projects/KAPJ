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
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"
            integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
            crossorigin="anonymous"></script>
    <script src="https://www.google.com/recaptcha/api.js"></script>
    <script src="https://npmcdn.com/tether@1.2.4/dist/js/tether.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.17.0/moment-with-locales.js"></script>
    <script src="https://rawgit.com/tempusdominus/bootstrap-4/master/build/js/tempusdominus-bootstrap-4.min.js"></script>
    <link href="https://rawgit.com/tempusdominus/bootstrap-4/master/build/css/tempusdominus-bootstrap-4.min.css"
          rel="stylesheet"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons"
          rel="stylesheet">
</head>
<body>

<h3><spring:message code="menu.newVisit"/></h3>

<form:form method="post" action="addPatient.html" modelAttribute="visit" style="padding-top: 5px">
    <div class="form-group">
        <form:label path="doctor"><spring:message code="label.role"/></form:label>
        <form:select path="doctor" multiple="false" class="form-control">
            <form:options items="${doctorsList}" itemValue="id" itemLabel="lastName"/>
        </form:select>
        <form:errors path="doctor"/>

    </div>

    <div class="form-group">
        <form:label path="doctor"><spring:message code="chooseDate"/></form:label>
        <div class="input-group date" id="datetimepicker" data-target-input="nearest">
            <span class="input-group-addon material-icons" data-target="#datetimepicker" data-toggle="datetimepicker"
                  class="material-icons">today</span>
            <input type="text" class="form-control datetimepicker-input"
                   data-target="#datetimepicker1"/>
        </div>
    </div>

    <div class="form-group">
        <button type="submit" class="btn btn-info"><spring:message code="bookVisit"/></button>
    </div>

</form:form>
<script>
    $(function () {
        $('#datetimepicker').datetimepicker({
            format: 'DD/MM/YYYY HH:mm',
            minDate: new Date(),
            stepping: 15,
            enabledHours: [6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19]
        });
    });
</script>
</body>
</html>
