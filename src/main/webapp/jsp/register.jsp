<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>注册</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body class="login-body">
<div class="register-box">
    <h2>用户注册</h2>

    <c:if test="${not empty error}">
        <div class="error">${error}</div>
    </c:if>

    <form action="${pageContext.request.contextPath}/register" method="post">
        <div class="form-group">
            <label for="reg-username">用户名：</label>
            <input type="text" id="reg-username" name="username" required>
        </div>

        <div class="form-group">
            <label for="reg-password">密码：</label>
            <input type="password" id="reg-password" name="password" required>
        </div>

        <button type="submit" class="register-button">注册</button>
    </form>

    <p style="text-align:center; margin-top:20px;">
        已有账号？<a href="${pageContext.request.contextPath}/jsp/login.jsp">立即登录</a>
    </p>
</div>
</body>
</html>