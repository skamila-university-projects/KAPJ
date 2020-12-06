<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<h1><spring:message code="label.menu"/></h1>
<a href="/appUsers.html"><spring:message code="label.addAppUser"/></a><br/>
<a href="/addresses.html"><spring:message code="label.address"/></a><br/>

<a href="/exampleOne.jsp">Example 1</a><br/>
<a href="/exampleTwo.jsp">Example 2</a><br/>
<a href="/exampleThree.jsp">Example 3</a>

<a href="/exampleOne.jsp"></a>
<a href="/exampleTwo.jsp"></a>
<a href="/exampleThree.jsp"></a>

<br>
<div>
    <c:if test="${pagrContext.request.userPrincipal.name != null}">
        <p>
            <a href="/logout">Logout</a>
        </p>
    </c:if>
</div>