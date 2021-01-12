<%--
  Created by IntelliJ IDEA.
  User: jaroslaw
  Date: 12/24/20
  Time: 4:30 PM
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
<header>
    <%@ include file="header.jsp" %>
</header>
<%--<script>--%>
<%--    function myFunction(){--%>
<%--        const x = document.getElementsByName("password")[0].value;--%>
<%--        const y = document.getElementsByName("passwordConfirm")[1].value;--%>
<%--        const ok = true;--%>
<%--        if(x !== y)--%>
<%--        {--%>
<%--            alert("Passwords do not match");--%>
<%--        }--%>
<%--        return ok;--%>
<%--    }--%>
<%--</script>--%>
<section class="login-page">
    <h2 id="register">Załóż konto</h2>
    <form:form modelAttribute="newUser" action="${pageContext.request.contextPath}/register" method="POST" htmlEscape="true">
    <div class="form-group">
        <label for="firstName"></label>
        <form:input type="text" class="form-control" id="firstName" name="firstName" placeholder="podaj imię" required="required" path="firstName"/>
        <form:errors path="firstName"/>
    </div>
    <div class="form-group">
        <label for="lastName"></label>
        <form:input type="text" class="form-control" id="lastName" name="lastName" placeholder="podaj nazwisko" required="required" path="lastName"/>
        <form:errors path="lastName"/>
    </div>
        <div class="form-group">
            <label for="lastName"></label>
            <form:input type="text" class="form-control" id="username" name="username" placeholder="podaj login" required="required" path="username"/>
            <form:errors path="lastName"/>
        </div>
    <div class="form-group">
        <label for="email"></label>
        <form:input type="text" class="form-control" id="email" name="email" placeholder="podaj email" required="required" path="email"/>
        <form:errors path="email"/>
    </div>
    <div class="form-group">
        <label for="password"></label>
        <form:input type="password" class="form-control" id="password" name="password" placeholder="podaj hasło" required="required" path="password"/>
        <form:errors path="password"/>
    </div>
    <div class="form-group">
        <form:input type="password" class="form-control" id="passwordConfirm" name="passwordConfirm" placeholder="Powtórz hasło" required="required" path="passwordConfirm"/>
<%--              <c:if test="${passwordConfirm!=password}">Wpisałeś różne hasła</c:if>--%>
        <form:errors path="passwordConfirm"/>
    </div>

        <div class="form-group form-group--buttons">
            <a href="${pageContext.request.contextPath}login.jsp" class="btn btn--without-border">Zaloguj się</a>
            <button class="btn" type="submit">Załóż konto</button>
        </div>
    </form:form>
</section>
<%@include file="footer.jsp" %>
<script src="<c:url value="${pageContext.request.contextPath}resources/js/app.js"/>"></script>
</body>
</html>

