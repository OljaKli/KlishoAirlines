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

<table class="bordered">
    <tr>
        <th>Flight</th>
        <th>from</th>
        <th>to</th>
    </tr>

    <sql:query dataSource="jdbc/ProdDB" var="flights">SELECT id, flightNumber, apfrom, apto FROM Flight;</sql:query>
    <c:forEach var="flight" items="${flights.rows}">
        <tr>
            <td>${flight.flightNumber}</td>
            <td>${flight.apfrom}</td>
            <td>${flight.apto}</td>
        </tr>
    </c:forEach>
</table>


</body>
</html>
