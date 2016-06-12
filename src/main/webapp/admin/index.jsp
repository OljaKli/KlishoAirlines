<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>

<fmt:setBundle basename="../l10n" var="l10n" />

<html>
<head>
    <title><fmt:message bundle="${l10n}" key="admin.index.title"/></title>
</head>
<body>
<a href="../index.jsp"><fmt:message bundle="${l10n}" key="admin.index.back"/></a><br/>


<form action="${pageContext.request.contextPath}/admin/" method="post">
    <fmt:message bundle="${l10n}" key="admin.flight.number"/>:  <input type="text" name="flightNumber" /><br/>
    <fmt:message bundle="${l10n}" key="admin.flight.from"/>:           <input type="text" name="from" /><br/>
    <fmt:message bundle="${l10n}" key="admin.flight.to"/>:             <input type="text" name="to" /><br/>
    <fmt:message bundle="${l10n}" key="admin.flight.depart"/>: <input type="text" name="departureTime" /><br/>
    <!-- галочки для дней недели -->
    <input type="submit" value="Create">
</form>


<table class="bordered">
    <tr>
        <th><fmt:message bundle="${l10n}" key="admin.flight.number"/></th>
        <th><fmt:message bundle="${l10n}" key="admin.flight.from"/></th>
        <th><fmt:message bundle="${l10n}" key="admin.flight.to"/></th>
        <th><fmt:message bundle="${l10n}" key="admin.flight.depart"/></th>
        <th><fmt:message bundle="${l10n}" key="admin.flight.days"/></th>
        <th></th>
    </tr>
    <c:forEach var="flight" items="${flights}">
        <tr>
            <td>${flight.flightNumber}</td>
            <td>${flight.from}</td>
            <td>${flight.to}</td>
            <td>${flight.departureTimeStr}</td>
            <td>${flight.daysOfWeekStr}</td>
            <td><a href="${pageContext.request.contextPath}/admin/?delete=${flight.id}"><fmt:message bundle="${l10n}" key="admin.flight.delete"/></a></td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
