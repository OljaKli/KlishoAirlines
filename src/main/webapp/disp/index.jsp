<%--
  Created by IntelliJ IDEA.
  User: Ola-Mola
  Date: 05/06/16
  Time: 14:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>


<fmt:setBundle basename="../l10n" var="l10n"/>

<html>
<head>
    <title><fmt:message bundle="${l10n}" key="disp.index.title"/></title>
</head>
<body>
<a href="../index.jsp"><fmt:message bundle="${l10n}" key="disp.index.back"/></a><br/>

<%----%>
<form action="${pageContext.request.contextPath}/disp/" method="post">


    <fmt:message bundle="${l10n}" key="disp.flight"/>
    <select name="flights">
        <c:forEach var="flight" items="${flights}">
            <option value="${flight.id}">${flight.flightNumber}</option>
        </c:forEach>
    </select><br/>

    <fmt:message bundle="${l10n}" key="disp.flight.pilot"/>
    <select name="employee">
        <c:forEach var="employee" items="${pilots}">
            <option value="${employee.id}">${employee.shortString}</option>
        </c:forEach>
    </select><br/>


    <fmt:message bundle="${l10n}" key="disp.flight.navigator"/>
    <select name="navigator">
        <c:forEach var="employee" items="${navigators}">
            <option value="${employee.id}">${employee.shortString}</option>
        </c:forEach>
    </select><br/>

        <fmt:message bundle="${l10n}" key="disp.flight.radiop"/>
    <select name="radiop">
        <c:forEach var="employee" items="${radiops}">
            <option value="${employee.id}">${employee.shortString}</option>
        </c:forEach>
    </select><br/>

        <fmt:message bundle="${l10n}" key="disp.flight.hostess1"/>
    <select name="hostess1">
        <c:forEach var="employee" items="${hostess}">
            <option value="${employee.id}">${employee.shortString}</option>
        </c:forEach>
    </select><br/>

        <fmt:message bundle="${l10n}" key="disp.flight.hostess2"/>
    <select name="hostess2">
        <c:forEach var="employee" items="${hostess}">
            <option value="${employee.id}">${employee.shortString}</option>
        </c:forEach>
    </select><br/>

        <fmt:message bundle="${l10n}" key="disp.flight.hostess3"/>
    <select name="hostess3">
        <c:forEach var="employee" items="${hostess}">
            <option value="${employee.id}">${employee.shortString}</option>
        </c:forEach>
    </select><br/>

    <input type="submit" value=<fmt:message bundle="${l10n}" key="disp.pilot.select"/>><br/>
</form>

<table class="bordered">
    <tr>
        <th><fmt:message bundle="${l10n}" key="disp.flight"/></th>
        <th><fmt:message bundle="${l10n}" key="disp.flight.pilot"/></th>
        <th><fmt:message bundle="${l10n}" key="disp.engineers"/></th>
        <th><fmt:message bundle="${l10n}" key="disp.hostess"/></th>

        <th></th>
    </tr>

    <c:forEach var="assign" items="${assignments}">
    <tr>
        <td>${assign.flight.flightNumber}</td>
        <td>
                ${assign.pilot.shortString}
        </td>
        <td>
                ${assign.navigator.shortString}<br/>
                ${assign.radiop.shortString}<br/>
        </td>
        <td>
                ${assign.hostess1.shortString}<br/>
                ${assign.hostess2.shortString}<br/>
                ${assign.hostess3.shortString}
        </td>


    </tr>
    </c:forEach>

    <%----%>
</body>
</html>
