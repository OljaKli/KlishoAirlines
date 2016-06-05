<%@ page session="true" contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setBundle basename="l10n" var="l10n" />

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Klisho Airlines login</title>
</head>
<body>
User '<%=request.getRemoteUser()%>' has been logged out.
<% session.invalidate(); %>

<br/><br/>
<%--<a href="login.jsp">Login</a>--%>
<a href="./index.jsp"><fmt:message bundle="${l10n}" key="logout.back"/></a><br/>

</body>
</html>