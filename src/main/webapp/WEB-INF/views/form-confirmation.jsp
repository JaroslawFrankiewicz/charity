<%--
  Created by IntelliJ IDEA.
  User: jaroslaw
  Date: 12/24/20
  Time: 4:33 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="pl">
<head>
    <%@include file="head.jsp" %>
    <title>Charity Home Page</title>
</head>
<body>
<header>
</header>
<%@include file="header.jsp" %>

    <div class="slogan container container--90">
        <h2>
            Dziękujemy za przesłanie formularza Na maila prześlemy wszelkie
            informacje o odbiorze.
        </h2>
    </div>

<%@include file="footer.jsp" %>
<script src="<c:url value="${pageContext.request.contextPath}resources/js/app.js"/>"></script>
</body>
</html>

