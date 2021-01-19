<%--
  Created by IntelliJ IDEA.
  User: jaroslaw
  Date: 12/20/20
  Time: 4:29 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="pl">
<head>
    <%@ include file="head.jsp" %>
    <title>Charity Home Page</title>
</head>
<body>
    <%@ include file="header.jsp" %>
<section class="login-page">
    <h2 id="login">Zaloguj się</h2>
    <form method="post">
        <div class="form-group">
<%--            <label>--%>
<%--                <input type="email" name="email" placeholder="Email" />--%>
<%--            </label>--%>
            <label>
                <input type="text" name="username" placeholder="username" />
            </label>
        </div>
        <div class="form-group">
            <label>
                <input type="password" name="password" placeholder="Hasło" />
            </label>
            <a href="#" class="btn btn--small btn--without-border reset-password">Przypomnij hasło</a>
        </div>

        <div class="form-group form-group--buttons">
            <a href="#" class="btn btn--without-border">Załóż konto</a>
            <button class="btn" type="submit">Zaloguj się</button>
        </div>
    </form>
</section>
    <%@include file="footer.jsp" %>
    <script src="<c:url value="${pageContext.request.contextPath}/resources/js/app.js"/>"></script>
</body>
</html>

