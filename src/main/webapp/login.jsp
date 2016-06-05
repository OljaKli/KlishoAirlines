<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setBundle basename="l10n" var="l10n" />

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Klisho Airlines login</title>
</head>
<body>
<h1><fmt:message bundle="${l10n}" key="login.message"/></h1>

<form method="POST" action="j_security_check" >
    <fmt:message bundle="${l10n}" key="login.username"/>: <input type="text" name="j_username"/><br/>
    <fmt:message bundle="${l10n}" key="login.password"/>: <input type="password" name="j_password"/><br/>
    <input type="submit" />
</form>
<font color="red"><c:if test="${not empty param.errMsg}">
    <c:out value="${param.errMsg}" />
</c:if></font>
</body>
</html>