<%--
  Created by IntelliJ IDEA.
  User: Ola-Mola
  Date: 29/05/16
  Time: 12:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<c:choose>
    <c:when test="${param.locale=='RU'}">
        <fmt:setLocale value="ru_RU" scope="session"/>
    </c:when>
    <c:otherwise>
        <fmt:setLocale value="en_US" scope="session"/>
    </c:otherwise>
</c:choose>

<fmt:setBundle basename="l10n" var="l10n" />


<html>
<head>
    <title><fmt:message bundle="${l10n}" key="index.title"/></title>
</head>
<body>
<fmt:message bundle="${l10n}" key="index.title"/>
<a href="./index.jsp?locale=EN">EN</a>
<a href="./index.jsp?locale=RU">RU</a>
<br/>



<a href="./admin/"><fmt:message bundle="${l10n}" key="index.admPage"/></a><br/>
<a href="./disp/"><fmt:message bundle="${l10n}" key="index.dispPage"/></a><br/>
<a href="./logout.jsp"><fmt:message bundle="${l10n}" key="index.logout"/></a><br/>
</body>
</html>


