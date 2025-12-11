<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>用户登录</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <script>
        function refreshCaptcha() {
            var captchaImg = document.getElementById('captchaImg');
            captchaImg.src = '${pageContext.request.contextPath}/captcha?t=' + new Date().getTime();
        }
    </script>
</head>
<body class="login-body">
<div class="login-box">
    <h2>用户登录</h2>

    <c:if test="${not empty error}">
        <div class="error">${error}</div>
    </c:if>

    <form action="${pageContext.request.contextPath}/login" method="post">
        <div class="form-group">
            <label for="username">用户名：</label>
            <input type="text" id="username" name="username" required placeholder="请输入用户名">
        </div>

        <div class="form-group">
            <label for="password">密码：</label>
            <input type="password" id="password" name="password" required placeholder="请输入密码">
        </div>

        <div class="form-group">
            <label for="captcha">验证码：</label>
            <div class="captcha-group">
                <input type="text" id="captcha" name="captcha" required placeholder="请输入验证码" class="captcha-input">
                <img id="captchaImg"
                     src="${pageContext.request.contextPath}/captcha"
                     alt="验证码"
                     class="captcha-img"
                     onclick="refreshCaptcha()"
                     title="点击刷新验证码">
            </div>
        </div>

        <button type="submit" class="register-button">登录</button>
    </form>

    <p style="text-align:center; margin-top:20px;">
        还没有账号？<a href="${pageContext.request.contextPath}/jsp/register.jsp">立即注册</a>
    </p>
</div>
</body>
</html>